package com;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController 
{

	
public static Map<String,String> users = new HashMap<String,String>();
public static List <Locations> l = new ArrayList<Locations>();
public static List <Hotels> h = new ArrayList<Hotels>();
public static Map<String,ArrayList<Item>> menu = new HashMap<String,ArrayList<Item>>();
public  Map<String,ArrayList<OrderDetails>> order = new HashMap<String,ArrayList<OrderDetails>>();
public Map<String,ArrayList<Invoice>> invoices = new HashMap<String,ArrayList<Invoice>>();
public Map<String,ArrayList<Rating>> randr = new HashMap<String,ArrayList<Rating>>();


public static String hotelName = new String("");
public static String username = new String("");
public static int orderid=555;
public int bill;
static // Seed Data  
{
	l.add(new Locations("Hitech-city"));
	l.add(new Locations("Gachibowli"));
	
	h.add(new Hotels("1","swagath","Hitech-city"));
	h.add(new Hotels("2","flechazo","Hitech-city"));
	h.add(new Hotels("3","taj","Gachibowli"));
	
	menu.put("swagath", new ArrayList<Item>());
	menu.put("flechazo", new ArrayList<Item>());
	menu.put("taj", new ArrayList<Item>());
	
	menu.get("swagath").add(new Item("biryani",100));
	menu.get("swagath").add(new Item("ice-cream",50));
	menu.get("flechazo").add(new Item("noodles",70));
	menu.get("taj").add(new Item("manchuria",90));
}

@RequestMapping("/login")
public String login()
{
	order = new HashMap<String,ArrayList<OrderDetails>>();	
return "login";	
}
	
@RequestMapping("/")
public String home(Model model)
{
username=CustomAuth.username.toUpperCase();
model.addAttribute("name",username);
model.addAttribute("locations", l);	
return "home"; 	
}

@RequestMapping(value="/",method=RequestMethod.POST)
public String hotels(@ModelAttribute("inp") Locations inp,Model model)
{
	
	List <Hotels> temp = new ArrayList<Hotels>();
	for(Hotels x :h)
	{
		if(x.location.equals(inp.name))
			temp.add(x);
	}
	
	model.addAttribute("place", inp.name);
	model.addAttribute("hotels",temp);
	return "hotels";	
}

@RequestMapping(value="/menu",method=RequestMethod.POST)
public String menu(@ModelAttribute("inp") Hotels inp,Model model)
{
if(inp.name!=null||inp.name!="")
hotelName=inp.name; 	
ArrayList <Item> temp = menu.get(hotelName); 
model.addAttribute("place",hotelName);
model.addAttribute("menu",temp);

return "menu";
}


@RequestMapping(value="/addItem",method=RequestMethod.POST)
public String menu(@ModelAttribute("inp") OrderDetails inp,Model model)
{
	//System.out.println("not added!"+inp.name+inp.quantity+inp.price);

	if(inp.quantity>0)
	{
	int flag=0;
	if(order.get(hotelName)==null)
	{	
		ArrayList<OrderDetails> al=new ArrayList<OrderDetails>();
		al.add(new OrderDetails(inp.name,inp.quantity,inp.price));
		order.put(hotelName,al);
	}
		else
	{
		for(OrderDetails x : order.get(hotelName))
			if(x.name.equals(inp.name))
			{
				x.quantity+=inp.quantity;
				System.out.println(x.name +"!");
				flag=1;
			}
	
		if(flag==0)
			{
			System.out.println("added!");
			order.get(hotelName).add(new OrderDetails(inp.name,inp.quantity,inp.price));
			}
	}
	
	}


ArrayList <Item> temp = menu.get(hotelName); 
model.addAttribute("place",hotelName);
model.addAttribute("menu",temp);

int totalBill=0;
ArrayList<OrderDetails> temp1 = new ArrayList<OrderDetails>();

//Set<String> keys = order.keySet();
//for(String x : keys )
	for(OrderDetails y : order.get(hotelName))
		{
		temp1.add(new OrderDetails(y.getName(),y.getQuantity(),y.quantity*y.getPrice()));
		totalBill+=y.quantity*y.getPrice();
		}

bill=totalBill;
model.addAttribute("cart", temp1);
model.addAttribute("totalBill",totalBill);
return "menu";
}

@RequestMapping(value="removeItem",method=RequestMethod.POST)
public String removeItem(@ModelAttribute("inp") OrderDetails inp,Model model)
{
	for(OrderDetails x : order.get(hotelName))
			if(x.getName().equals(inp.name))
			{
				order.get(hotelName).remove(x);
				break;
			}
	ArrayList <Item> temp = menu.get(hotelName); 
	model.addAttribute("place",hotelName);
	model.addAttribute("menu",temp);
	
	int totalBill=0;
	ArrayList<OrderDetails> temp1 = new ArrayList<OrderDetails>();

	//Set<String> keys = order.keySet();
	//for(String x : keys )
		if(order.get(hotelName)!=null)
		for(OrderDetails y : order.get(hotelName))
			{
			temp1.add(new OrderDetails(y.getName(),y.getQuantity(),y.quantity*y.getPrice()));
			totalBill+=y.quantity*y.getPrice();
			}

		bill=totalBill;
	model.addAttribute("cart", temp1);
	model.addAttribute("totalBill",totalBill);
	return "menu";
}

@RequestMapping(value="/checkout",method = RequestMethod.POST)
public String checkout(Model model)
{
if(invoices.get(username)==null)
{
ArrayList<Invoice> al = new ArrayList<Invoice>();
al.add(new Invoice(orderid++,hotelName,bill,order.get(hotelName)));
invoices.put(username, al);
}
else
invoices.get(username).add(new Invoice(orderid++,hotelName,bill,order.get(hotelName)));	
//order.remove("hotelName");
return "checkout";	
}

@RequestMapping(value="/submit-rating",method=RequestMethod.POST)
public String review(@ModelAttribute("inp") Rating inp,Model model)
{
	Rating temp = new Rating(username,inp.rating,inp.review);
	if(randr.get(hotelName)==null)
	{
		ArrayList<Rating> temp1 = new ArrayList<Rating>();
		temp1.add(temp);
		randr.put(hotelName,temp1);
	}
	else
		randr.get(hotelName).add(temp);
	
	return "thanks";
}

@RequestMapping("view-reviews")
public String viewReviews(Model model)
{
	model.addAttribute("name",hotelName);
	model.addAttribute("reviews",randr.get(hotelName));

	return "view-reviews";
}


@RequestMapping(value="/view-orders",method = RequestMethod.POST)
public String viewUsers(Model model)
{
	model.addAttribute("name", username);
	model.addAttribute("invoices",invoices.get(username));
	return "vieworder";
}


@RequestMapping(value="/register",method = RequestMethod.GET)
public ModelAndView register()
{
	return new ModelAndView("register","command",new User());
}

@RequestMapping(value="/register",method = RequestMethod.POST)
public String addUser(@ModelAttribute("inp") User inp , Model model)
{
	if(!users.containsKey(inp.uname)&&inp.uname!=""&&inp.password!="")
	{
		users.put(inp.uname, inp.password);
		return "login";
	}
	else
	{
		model.addAttribute("error",  "Invalid credentials or UserName is already taken! please choose another one.");
		return "register";
	}
}

@RequestMapping("/login-failure")
public String loginFail()
{
return "login-failure"; 	
}




}

