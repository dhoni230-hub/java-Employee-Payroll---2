import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class Employe
{
    int id;
    String name, department;
    double salary;

    Employe()
    {
        id = 0;
        name = "";
        salary = 0;
        department = "";
    }

 

    Employe(int id, String name, double salary, String department)
    {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
	
	Employe(Employee e)
    {
        id = e.id;
        name = e.name;
        salary = e.salary;
        department = e.department;
    }

    double allowance()
	{
		 return salary * 0.10;
	}		 

    double deduction()
	{
         return salary * 0.05;		
 	}
   

    double netsalary()
    {
        return salary + allowance() - deduction();
    }

    public String toString()
    {
        return id + "," + name + "," + salary + "," + department + "," + allowance() + "," + deduction() + "," + netsalary();
    }
}

public class A53 extends JFrame
{
	 ArrayList<Employe> list = new ArrayList<>();
     JTextField id, name, salary;
	 JComboBox<String> dept;
	 JTextArea output;
	 
	 A53()
	 {
		  setTitle("Employee Payroll System");
		  setSize(600,600);
		  setLayout(null);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
		   JLabel l1 = new JLabel("Employee ID: ");
		   l1.setBounds(30,30,120,30);
		   add(l1);
		   
		   id = new JTextField();
		   id.setBounds(160,30,180,30);
		   add(id);
		   
		   JLabel l2 = new JLabel("Name: ");
		   l2.setBounds(30,80,120,30);
		   add(l2);
		   
		   name = new JTextField();
		   name.setBounds(160,80,180,30);
		   add(name);
		   
		   JLabel l3 = new JLabel("Basic Salary: ");
		   l3.setBounds(30,130,120,30);
		   add(l3);
		   
		   salary = new JTextField();
		   salary.setBounds(160,130,180,30);
		   add(salary);
		   
		   JLabel l4 = new JLabel("Department: ");
		   l4.setBounds(30,180,120,30);
		   add(l4);
		   
		   String[] de =  {"IT","HR","Sales","Accounter"};
		   
		   dept = new JComboBox<>(de);
		   dept.setBounds(160,180,180,30);
		   add(dept);
		   
		   JButton b1 = new JButton("Add");
		   b1.setBounds(30,230,100,30);
		   add(b1);
		   
		   JButton b2 = new JButton("Save");
		   b2.setBounds(150,230,100,30);
		   add(b2);
		   
		   JButton b3 = new JButton("Load");
		   b3.setBounds(270,230,100,30);
		   add(b3);
		   
		   JButton b4 = new JButton("View All");
		   b4.setBounds(390,230,100,30);
		   add(b4);
		   
		   
		   output = new JTextArea();
		   output.setEditable(false);
		   
		   JScrollPane sp = new JScrollPane(output);
		   sp.setBounds(30,290,520,240);
		   add(sp);
		   
		   b1.addActionListener(e -> addEmploye());
		   b2.addActionListener(e -> saveEmploye());
		   b4.addActionListener(e -> viewEmploye());
		   b3.addActionListener(e -> loadEmploye());

		   
		   setVisible(true);
	 }
	 
 void addEmploye()
 { 
	  
	 try 
	  {
	   int empid = Integer.parseInt(id.getText());
       String empname = name.getText();	   
	   double empsalary = Double.parseDouble(salary.getText()); 
             

          if(empid <= 0 || empname.isEmpty() || empsalary < 0)
		  {
			 JOptionPane.showMessageDialog(this,"Enter a Valid Employe Details."); 
			   return;
		  }
		  
		  
		  for(Employe e : list)
		  {
			  if(e.id == empid)
			  {
				  JOptionPane.showMessageDialog(this,"Employe Alredy Existe..");
				  return;
			  }
		  }


			 
		 Employe e = new Employe(
		 empid,
		 empname,
		 empsalary,
		 dept.getSelectedItem().toString());
		 
		 list.add(e);
		 
		 JOptionPane.showMessageDialog(this,"Employe addes SuccessFully \n net Salaey: Rs. " + e.netsalary());
		 id.setText("");
		 name.setText("");
		 salary.setText("");
	  }
	  
	  catch(NumberFormatException ex)
	  {
		 JOptionPane.showMessageDialog(this,"Enter a Valid Id and Salary");		  
	  }
 }
	 

  void saveEmploye()
  {
	   try 
	   { 
	     BufferedWriter bw = new BufferedWriter(new FileWriter("Employe.txt"));
		 
		 for(Employe e : list)
		 {
			 bw.write(e.toString());
			 bw.newLine();
		 }
		 
		 bw.close();
		 
		 JOptionPane.showMessageDialog(this,"Employe Save Successsfull");
	   }
	   
	     catch(IOException ex)
		 {
			  JOptionPane.showMessageDialog(this,"File Error: "+ex.getMessage());
		 }
  }
  
  
  void viewEmploye()
  {
	    output.setText("ID\tName\tSalary\tDepartment\tAllowance\tDeducation\tNetSalary\n");
		output.append("---------------------------------------------------------------------------------------------------------------------------------------------------\n");
		
		for(Employe e: list)
		{
			 output.append(e.id + "\t" + e.name + "\t" + e.salary +"\t" + e.department + "\t" + e.allowance() + "\t" + e.deduction() +"\t"+  e.netsalary() + "\n");
		}
  }
  
  
  
  void loadEmploye()
  {
	  list.clear();
	  
	  try {
		    BufferedReader br = new BufferedReader(new FileReader("Employe.txt"));
			String li;
			
			while((li = br.readLine()) != null)
			{
				String[] data = li.split(",",-1);
				
				if(data.length == 7)
				{
					Employe e = new Employe(Integer.parseInt(data[0]),
					data[1],
					Double.parseDouble(data[2]),
					data[3]);
					
					list.add(e);
				}
			
			}	
			br.close();
			JOptionPane.showMessageDialog(this,"Employe Load Successfull.");
			
			viewEmploye();
	  }
	  
	   catch(IOException  ex)
	   {
		   JOptionPane.showMessageDialog(this,"Unable To Load File");
	   }
		
	  
	  
  }
  

	 public static void main(String args[])
	 {
	      	 new A53();
	 }
}
		   
	 