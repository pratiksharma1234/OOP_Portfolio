package ooop;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import uk.ac.leedsbeckett.oop.LBUGraphics;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;

public class GraphicsSystem extends LBUGraphics{
	private boolean penDownStatus = true;
	private static final Component MainFrame = null; 
	//This code declares a private static final Component variable called MainFrame, which is initialized to null.
	public static void main(String[] args)
    {
            new GraphicsSystem(); //create instance of class that extends LBUGraphics (could be separate class without main), gets out of static context
    }
	private JTextArea commandDisplay;
	private boolean fill; //Setting the fill status
	private boolean isSaved = true;
	private boolean confirmSaveBeforeDestructiveAction() {
	    if (!isSaved) {
	        int choice = JOptionPane.showConfirmDialog(null,
	                "You have unsaved changes. Do you want to save before proceeding?",
	                "Unsaved Changes",
	                JOptionPane.YES_NO_CANCEL_OPTION,
	                JOptionPane.WARNING_MESSAGE);

	        if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) {
	            return false; // cancel the clear or destructive action
	        } else if (choice == JOptionPane.YES_OPTION) {
	            save();
	        }
	    }
	    return true; // safe to proceed
	}

	public GraphicsSystem() 
	{
		//Declaring all these in order to display the output as a frame
        JFrame MainFrame = new JFrame();            //create a frame to display the turtle panel on
        MainFrame.setLayout(new FlowLayout());     //not strictly necessary
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //to close the window when asked
        MainFrame.add(this);                       //"this" is this object that extends turtle graphics so we are adding a turtle graphics panel to the frame
        MainFrame.setSize(820,445);                //set the frame to a size we can see
        commandDisplay = new JTextArea(10, 60);
        commandDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(commandDisplay);
        MainFrame.add(scrollPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem createItem = new JMenuItem("Create");

        createItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int option = chooser.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String fileName = selectedFile.getName().toLowerCase();

                if (fileName.endsWith(".txt")) {
                    try (Scanner scanner = new Scanner(selectedFile)) {
                        while (scanner.hasNextLine()) {
                            String command = scanner.nextLine();
                            processCommand(command);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error reading file");
                    }
                } else if (fileName.endsWith(".png")) {
                    try {
                        BufferedImage image = ImageIO.read(selectedFile);
                        this.setBufferedImage(image);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error loading image");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Unsupported file type");
                }
            }
        });

        fileMenu.add(createItem);
        menuBar.add(fileMenu);
        MainFrame.setJMenuBar(menuBar);
        MainFrame.setVisible(true);               //now display it
        for (Component comp : this.getComponents()) {// Convert the "ok" button to uppercase "OK"
            if (comp instanceof javax.swing.JButton) {
                javax.swing.JButton btn = (javax.swing.JButton) comp;
                if (btn.getText().equalsIgnoreCase("ok")) {
                    btn.setText("OK");  // set label to uppercase
                }
            }
        }

		//Calling already defined method to make the turtle face down and also make the penDown and display name at line
        drawOn(); //sets the pen to "down"
		displayMessage("PRATIK SHARMA"); //This code will make the turtle draw a message that says "I AM PRATIK SHARMA" on the canvas.
	}
	
	private ArrayList<String> list = new ArrayList<String>();  //This is a private variable declaration for an ArrayList of Strings.
	
	@Override
	public void processCommand(String command) {
		logCommand(command);
		
		try 
		{
			if(command.contains(" ")) //This code checks if the command string contains a space character.
				
			{
				String allCommand[] = command.split(" "); // This code will split a string (command) into multiple strings, stored in an array named allCommand.
				
				if (allCommand[0].equals("left") && Integer.parseInt(allCommand[1]) > 0) {
				    left(Integer.parseInt(allCommand[1]));
				    list.add(command);
				    isSaved = false;

				    return;
				} else if (allCommand[0].equals("right") && Integer.parseInt(allCommand[1]) > 0) {
				    right(Integer.parseInt(allCommand[1]));
				    list.add(command);
				    isSaved = false;

				    return;
				}
				    else if ((allCommand[0].equals("forward") || allCommand[0].equals("move"))) {
				        try {
				            int distance = Integer.parseInt(allCommand[1]);

							// Calculate future position based on current angle
				            double radians = Math.toRadians(getDirection());
				            int futureX = xPos + (int) (distance * Math.sin(radians));
				            int futureY = yPos + (int) (distance * Math.cos(radians));


				            if (distance < 0) {
				                JOptionPane.showMessageDialog(null, "Negative distances are not allowed!", "Error", JOptionPane.ERROR_MESSAGE);
				            } else if (futureX < 0 || futureX > getWidth() || futureY < 0 || futureY > getHeight()) {
				                JOptionPane.showMessageDialog(null, "Move out of bounds! Stay within screen.", "Error", JOptionPane.ERROR_MESSAGE);
				            } else {
				                forward(distance);
				                isSaved = false;
				                list.add(command);
				            }

				            return;
				        } catch (NumberFormatException ex) {
				            JOptionPane.showMessageDialog(null, "Invalid move parameter", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				    }


				//This code checks if the first element in the array allCommand is equal to "forward" and if the second element is greater than 0. If both conditions are true, it calls the method forward with the second element in the array as the parameter. It then adds the command to the list.
				
				    else if ((allCommand[0].equals("backward") || allCommand[0].equals("reverse"))) {
				        try {
				            int distance = Integer.parseInt(allCommand[1]);

				            // Calculate future position for negative movement
				            double radians = Math.toRadians(getDirection());
				            int futureX = xPos - (int) (distance * Math.sin(radians));
				            int futureY = yPos - (int) (distance * Math.cos(radians));


				            if (distance < 0) {
				                JOptionPane.showMessageDialog(null, "Negative distances are not allowed!", "Error", JOptionPane.ERROR_MESSAGE);
				            } else if (futureX < 0 || futureX > getWidth() || futureY < 0 || futureY > getHeight()) {
				                JOptionPane.showMessageDialog(null, "Move out of bounds! Stay within screen.", "Error", JOptionPane.ERROR_MESSAGE);
				            } else {
				                forward(-distance);
				                isSaved = false;
				                list.add(command);
				            }

				            return;
				        } catch (NumberFormatException ex) {
				            JOptionPane.showMessageDialog(null, "Invalid reverse parameter", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				        
				    }
				

				if(allCommand[0].equalsIgnoreCase("flag") && allCommand.length == 2) {
			        drawFlag(allCommand[1]); 
			        isSaved = false;// calls your flag drawing function
			        list.add(command);
			    }

				//This code checks if the first element in the array allCommand is equal to "backward" and if the second element is greater than 0. If both conditions are true, it calls the method backward with the second element in the array as the parameter. It then adds the command to the list.
				
				else if(allCommand[0].equals("square")== true && Integer.parseInt(allCommand[1])>0) 
				{
					square(Integer.parseInt(allCommand[1]));
					list.add(command);
					forward(0);
					isSaved = false;

				}
				//This code checks if the first element in the array allCommand is equal to "square" and if the second element is greater than 0. If both conditions are true, it calls the method square with the second element in the array as the parameter. It then adds the command to the list.
				
				else if(allCommand[0].equals("pencolor")==true && allCommand.length == 4 && Integer.parseInt(allCommand[1])>0 && Integer.parseInt(allCommand[2])>0 && Integer.parseInt(allCommand[3])>0) 
				//if the first element in the array of strings is "pencolor" and the length of the array is 4 and all the elements of the array are greater than 0

				{
					if(Integer.parseInt(allCommand[1])> 255 || Integer.parseInt(allCommand[2])> 255 || Integer.parseInt(allCommand[3])>255) 
						//if the value of any element of the array is greater than 255
					{
						JOptionPane.showMessageDialog(null,"Color value range from 0 to 255!","Error",JOptionPane.ERROR_MESSAGE);	
						//show an error message
					}
					else {
						setPenColour(new Color(Integer.parseInt(allCommand[1]), Integer.parseInt(allCommand[2]), Integer.parseInt(allCommand[3])));
						list.add(command);
						isSaved = false;

					} //otherwise, set the pen colour and add the command to the list
					
				}
				else if (allCommand[0].equalsIgnoreCase("pen") && allCommand.length == 2) {
				    String colorName = allCommand[1].toLowerCase();

				    switch (colorName) {
				        case "red":
				            setPenColour(Color.RED);
				            break;
				        case "green":
				            setPenColour(Color.GREEN);
				            break;
				        case "blue":
				            setPenColour(Color.BLUE);
				            break;
				        case "black":
				            setPenColour(Color.BLACK);
				            break;
				        case "white":
				            setPenColour(Color.WHITE);
				            break;
				        case "yellow":
				            setPenColour(Color.YELLOW);
				            break;
				        case "cyan":
				            setPenColour(Color.CYAN);
				            break;
				        case "magenta":
				            setPenColour(Color.MAGENTA);
				            break;
				        case "skyblue":
				            setPenColour(new Color(135, 206, 235)); // custom color
				            break;
				        default:
				            JOptionPane.showMessageDialog(null, "Unknown color name!", "Error", JOptionPane.ERROR_MESSAGE);
				            return;
				    }

				    list.add(command);
				    isSaved = false;
				    return;
				}

				else if (allCommand[0].equalsIgnoreCase("pen") && allCommand.length == 4) {
				    try {
				        int r = Integer.parseInt(allCommand[1]);
				        int g = Integer.parseInt(allCommand[2]);
				        int b = Integer.parseInt(allCommand[3]);

				        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
				            throw new NumberFormatException();
				        }

				        setPenColour(new Color(r, g, b));
				        list.add(command);
				        isSaved = false;
				    } catch (NumberFormatException e) {
				        JOptionPane.showMessageDialog(null, "Invalid RGB values! Each must be between 0 and 255.", "Error", JOptionPane.ERROR_MESSAGE);
				    }
				}

				else if(allCommand[0].equals("penwidth")== true && Integer.parseInt(allCommand[1])>0) 
				{
					setStroke(Integer.parseInt(allCommand[1]));
					list.add(command);
					isSaved = false;

				}
				//checks to see if the first element of the array allCommand is equal to the string "penwidth" and if the second element is greater than 0. if the condition is true, runs the setStroke method with the second element of the array as input. adds the command to the list
				
				
				
				else if (allCommand[0].equalsIgnoreCase("triangle") && allCommand.length == 4) {
				    try {
				        int a = Integer.parseInt(allCommand[1]);
				        int b = Integer.parseInt(allCommand[2]);
				        int c = Integer.parseInt(allCommand[3]);

				        if (a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a) {
				            drawCustomTriangle(a, b, c);
				            list.add(command);
				            isSaved = false;

				        } else {
				            JOptionPane.showMessageDialog(null, "Invalid triangle sides!", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				    } catch (NumberFormatException e) {
				        JOptionPane.showMessageDialog(null, "Please enter numeric values for triangle sides!", "Error", JOptionPane.ERROR_MESSAGE);
				    }				//This code checks if the first element of the array "allCommand" is equal to the string "triangle" and the length of the command is 4 and all the parameters are greater than 0, then a triangle is drawn and the command is added to the list and the turtle is moved forward by 0 units.

				    
				}

				else if(allCommand[0].equals("triangle")==true && Integer.parseInt(allCommand[1])>0)
				{
					triangle(Integer.parseInt(allCommand[1]));
					list.add(command);
					forward(0);
					isSaved = false;

				}
				//This code checks if the first element in the array allCommand is equal to "triangle" and if the second element is a positive number. If the statement is true, the triangle(int) method is called with the second element of the allCommand array as a parameter. The command is then added to the list and the forward(0) method is called.
				
				else if(allCommand[0].equals("circle")== true && Integer.parseInt(allCommand[1])>0) 
				{
					circle(Integer.parseInt(allCommand[1]));
					list.add(command);
					isSaved = false;

				}
				//This code checks if the first element of the array "allCommand" is equal to the string "circle", and also checks if the second element of the array is a positive integer. If both conditions are true, the function "circle" is called with the second element of the array as an argument, and the command is added to a list.
				
				else if(allCommand[0].equals("rectangle")==true && Integer.parseInt(allCommand[1])>0 && Integer.parseInt(allCommand[2])>0) 
				{
					rectangle(Integer.parseInt(allCommand[1]),Integer.parseInt(allCommand[2]));
					list.add(command);
					forward(0);
					isSaved = false;

				}
				//This code checks if the first element of the array allCommand is equal to the string "rectangle" and the first and second elements of the array allCommand are greater than 0, then the program will call the rectangle() method, passing in the first and second elements of the array allCommand as parameters, add the command to the list, and then call the forward() method, passing in 0 as a parameter.
				
				else if(Integer.parseInt(allCommand[1])<0) 
				{
					JOptionPane.showMessageDialog(null,"Parameter Not Bounded !","Error",JOptionPane.ERROR_MESSAGE);
				}
				//This code checks if the second parameter of the command (allCommand[1]) is less than 0. If it is, then a message dialog appears with an error message.
				
				else {
					JOptionPane.showMessageDialog(null, command + " is not a valid command","Error",JOptionPane.ERROR_MESSAGE);
				}
				//This code is an else statement that displays an error message if the command entered is not valid.
				
			}
			else {
				if(command.equals("left")==true)
		 		{
					left(90);
					list.add(command);
		 		}
				//This code checks if the command variable is equal to "turnleft", and if it is, it runs the turnLeft() function with an argument of 90, and adds the command to the "list" variable.
				
				else if(command.equals("right")==true)
		 		{
					right(90);
					list.add(command);
		 		}
				//This code checks if the command variable is equal to "turnright", and if it is, it runs the turnRight() function with an argument of 90, and adds the command to the "list" variable.			

				else if(command.equals("blue")==true) {
					setPenColour(PenColour.blue);
					list.add(command);
				}
				//This code checks if the command is "black", the pen colour is set to black and the command is added to the list.
				
				else if(command.equals("green")==true) {
					setPenColour(PenColour.green);
					list.add(command);
				}
				//This code checks if the command is "green", the pen colour is set to green and the command is added to the list.
				
				else if(command.equals("red")==true) {
					setPenColour(PenColour.red);
					list.add(command);
				}
				//This code checks if the command is "red", the pen colour is set to red and the command is added to the list.
				
				else if(command.equals("white")==true) {
					setPenColour(PenColour.white);
					list.add(command);
				}
				//This code checks if the command is "white", the pen colour is set to white and the command is added to the list.
				
				else if (command.equals("penup")) {
				    drawOff();
				    penDownStatus = false;
				    updatePenStatusMessage(); //display correct message if the pen is up
				    list.add(command);
				    isSaved = false;
				}

				else if (command.equals("pendown")) {
				    drawOn();
				    penDownStatus = true;
				    updatePenStatusMessage();  //display correct message if the pen is down
				    list.add(command);
				    isSaved = false;
				}



				//This code checks if the command is equal to "pendown". If it is, it calls the penDown() method and adds the command to the list.
				
				else if(command.equals("about")==true) {
					about();
					list.add(command);
				}

				//This code checks if the command is equal to "about". If it is, it calls the about() method and adds the command to the list.
				
				else if (command.equals("reset") == true) {
				    if (confirmSaveBeforeDestructiveAction()) {
				        reset();
				        drawOff();
				        isSaved = true;
				        list.clear();
				    }
				}

				//This code checks if the command is "reset", the reset() method is called, the penDown() method is called, and the command is added to the list.
				
				else if (command.equals("clear")) {
				    if (confirmSaveBeforeDestructiveAction()) {
				        clear();

				        // Move turtle to far left
				        setxPos(0);
				        setyPos(getHeight() / 2);

				        // Set direction to downward
				        while (getDirection() != 90) {
				            right(90); // adjust to downward
				        }

				        drawOn(); // pen ready
				        displayMessage("Pratik Sharma - Ready");

				        isSaved = true;
				        list.clear();
				    }
				}


				//This code checks if the command is equal to "clear". If it is, the "clear()" method is called and the command is added to the "list" variable.
				
				else if(command.equals("randomcolor")==true) {
					randomColor();
					list.add(command);
				}
				//This code checks if the command is "randomcolor", the method randomColor() is executed and the command is added to the list.
				
				else if(command.equals("help")==true) {
					help();
					list.add(command);
				}
				//This code checks if the command entered is "help", and if so, executes the help() function and adds the command to the list.
				
				else if(command.equals("save")==true) {
					save();
				}
				//This code checks if the command is equal to "save", the program will execute the save() method.
				
				else if(command.equals("load")==true) {
					load();
				}
				//This code checks if the command entered is "load", the program will execute the load() method.
				
				else if(command.equals("forward")==true)
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed !","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				//This code checks to see if the command is equal to "forward" and if it is, it displays an error message saying "No Parameter Passed!"
				
				else if(command.equals("backward")==true)
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed !","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				//This code checks to see if the command is equal to "backward" and if it is, it displays an error message saying "No Parameter Passed!"
				
				else if(command.equals("square")==true)
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed !","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				//This code checks to see if the command is equal to "square" and if it is, it displays an error message saying "No Parameter Passed!"
				
				else if(command.equals("pencolor")==true)
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed !","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				//This code checks to see if the command is equal to "pencolor" and if it is, it displays an error message saying "No Parameter Passed!"
				
				else if(command.equals("penwidth")==true)
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed !","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				//This code checks to see if the command is equal to "penwidth" and if it is, it displays an error message saying "No Parameter Passed!"
				
				else if(command.equals("circle")==true)
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed !","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				//This code checks to see if the command is equal to "circle" and if it is, it displays an error message saying "No Parameter Passed!"
				
				else if(command.equals("rectangle")==true)
		 		{
		 			JOptionPane.showMessageDialog(null, "No Parameter Passed !","Error",JOptionPane.ERROR_MESSAGE);
		 		}
				//This code checks to see if the command is equal to "rectangle" and if it is, it displays an error message saying "No Parameter Passed!"
				
				else {
					JOptionPane.showMessageDialog(null, command + " is not a valid command !","Error",JOptionPane.ERROR_MESSAGE);
				}
				//This code is an else statement that displays an error message if the command entered is not valid.
			}	
		}
		catch(Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Please, Provide Numerical Value !","Error",JOptionPane.ERROR_MESSAGE);
		}	
		//This code is a try/catch block used to catch an exception and display an error message in a JOptionPane.
	}
	private void logCommand(String command) {
	    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	    String logLine = "[" + timeStamp + "] " + command;

	    System.out.println(logLine); // Print to console

	    String fileName = "CommandLog_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt";

	    try (FileWriter fw = new FileWriter(fileName, true)) {
	        fw.write(logLine + "\n");
	    } catch (IOException e) {
	        System.err.println("Failed to write command log: " + e.getMessage());
	    }
	}
	private void updatePenStatusMessage() {
	    String message = penDownStatus ? "Pratik Sharma - Drawing" : "Pratik Sharma - Pen Lifted";
	    displayMessage(message);
	}

	private void updateStatusLabel(Graphics g) {
	    g.setColor(Color.BLACK); // or any contrasting color
	    String status = "Pratik Sharma - " + (penDownStatus ? "Drawing" : "Pen Lifted");
	    g.drawString(status, 20, getHeight() - 10); // bottom-left corner
	}

	private void fileloader() {
		
		int box = JOptionPane.showConfirmDialog(MainFrame, "Want to load file?");  //prompts user with popup to confirm if they want to load a file
		if (box == JOptionPane.YES_OPTION) {  //If the user answers yes to the question
			String confirm = JOptionPane.showInputDialog(MainFrame, "1. Load Image\n2. Load text commands"); //prompts user with popup to choose between loading an image or loading text commands
			if (confirm.equals("1")) {
				loadPhoto(); //calls the method loadPhoto
			} else if (confirm.equals("2")) {
				loadInstruct(); //calls the method loadInstruct
			} else {
				JOptionPane.showMessageDialog(MainFrame, "Instruct Not Valid", "Error", JOptionPane.ERROR_MESSAGE);
			} //display error message if the user selects anything else
		}
	}
	

	private void load(){
		
		int warn = JOptionPane.showConfirmDialog(MainFrame, "Want to save this file first?"); //Create an int called "warn", which is a question asking if the user wants to save the file
		
		if(warn == JOptionPane.YES_OPTION) {  //If the user answers yes to the question
			save(); //calls the method save
			fileloader(); //calls the method fileloader
		}
		else if(warn == JOptionPane.NO_OPTION) {  //If the user answers no to the question
			fileloader(); //calls the method fileloader
		}		
	}
	
	
	private void loadInstruct() {
	    JFileChooser chooseFile = new JFileChooser();
	    chooseFile.setDialogTitle("Open Command File");
	    int cfile = chooseFile.showOpenDialog(this);

	    if (cfile == JFileChooser.APPROVE_OPTION) {
	        Path location = Paths.get(chooseFile.getSelectedFile().getAbsolutePath());

	        try {
	            reset(); // reset turtle
	            drawOn();
	            isSaved = true;
	            list.clear();
	            commandDisplay.setText(""); // Clear text area

	            Scanner scan = new Scanner(location);
	            while (scan.hasNextLine()) {
	                String line = scan.nextLine();
	                processCommand(line); // Executes the command
	                if (commandDisplay != null) {
	                    commandDisplay.append(line + "\n"); // Show in UI
	                }
	                System.out.println(line); // Also print to console
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Error while loading commands.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	

	private void loadPhoto() {
		
	JFileChooser chooseFile = new JFileChooser();  //This line creates a new JFileChooser object called chooseFile
	JLabel label= new JLabel();  //This line creates a new JLabel object called label
	JFrame frame= new JFrame();  //This line creates a new JFrame object called frame
	chooseFile.setDialogTitle("Save as");  //This line sets the dialog title of chooseFile to "Save as"
	int cfile = chooseFile.showOpenDialog(this);  //This line shows an open dialog box and assigns the result to the int variable cfile
	String location=chooseFile.getSelectedFile().getAbsolutePath();  //This line retrieves the absolute path of the selected file and assigns it to the string variable location
	File file=new File(location);  //This line creates a new File object called file and sets its path to the string variable location
	if (cfile == JFileChooser.APPROVE_OPTION) {  //This line checks if the result of the open dialog box is "APPROVE_OPTION"
		try {
			ImageIcon icon = new ImageIcon(location);  //This line creates a new ImageIcon object called icon and sets its path to the string variable location
			label = new JLabel(icon);  //This line creates a new JLabel object called label and sets its icon to the ImageIcon object icon
			frame = new JFrame();  //This line creates a new JFrame object called frame
			frame.setTitle(file.getName());  //This line sets the title of the frame to the name of the File object file
			frame.setPreferredSize(new Dimension(800, 400));  //This line sets the preferred size of the frame to 800 by 400 pixels
			frame.setLayout(new FlowLayout());  //This line sets the layout of the frame to FlowLayout
			frame.getContentPane().add(label);  //This line adds the JLabel object label to the content pane of the frame
			frame.pack();  //This line packs the frame
			frame.setVisible(true);  //This line sets the frame to visible
		} 
		catch (Exception e) {  //This line catches any exceptions
			JOptionPane.showMessageDialog(MainFrame, "Error while loading image", "Error", JOptionPane.ERROR_MESSAGE);  //This line displays an error message if an exception is caught
			
		}
	}
}

	
	private void save() {
		
	int dbox = JOptionPane.showConfirmDialog(MainFrame, "Want to save file?");  //asks user if they want to save file and stores result in dbox
	if (dbox == JOptionPane.YES_OPTION) {  //if user selected yes
		String opt = JOptionPane.showInputDialog(MainFrame, "1. Save Photo\n2. Save text command:");  //asks user if they want to save photo or text and stores result in opt
		int cmd = Integer.parseInt(opt);  //converts opt from a string to an int
		if (cmd == 1) {  //if user selected save photo
			savePhoto();  //calls the savePhoto method
		} else if (cmd == 2) {  //if user selected save text
			instructSave();  //calls the instructSave method
		} else {  //if user did not select 1 or 2
			JOptionPane.showMessageDialog(MainFrame, "Instruct Not Valid", "Error", JOptionPane.ERROR_MESSAGE);  //displays an error message
		}
	}
	
}
	
	private void instructSave() {
		JFileChooser chooseFile = new JFileChooser(); //creates file chooser
		chooseFile.setDialogTitle("Save as"); //sets title of file chooser as "Save as"
		int cmd = chooseFile.showSaveDialog(this); //shows save dialog and stores returned integer in cmd
		if (cmd == JFileChooser.APPROVE_OPTION) { //if user clicks "save"
			Path location = Paths.get(chooseFile.getSelectedFile().getAbsolutePath()); //gets path of file
			try {
				Files.write(location, list); //writes list of commands to file
				JOptionPane.showMessageDialog(MainFrame, "All command saved.", "Success", JOptionPane.PLAIN_MESSAGE); //pop-up message to show command was saved
			}
			 catch (Exception e) { //if there is an error
				JOptionPane.showMessageDialog(MainFrame, "Error in saving command.", "Error", JOptionPane.ERROR_MESSAGE); //pop-up message to show error				
			}
		}
	}

	private void savePhoto() {
		
	JFileChooser chooseFile = new JFileChooser(); //creates a JFileChooser object called chooseFile
	chooseFile.setDialogTitle("Save as"); //sets the dialog title of the JFileChooser object to "Save as"
	int img = chooseFile.showSaveDialog(this); //displays the JFileChooser dialog and stores the response in an integer
	if (img == JFileChooser.APPROVE_OPTION) { //checks if the response was to approve the dialog
		try { //attempts to perform the following code
			Thread.sleep(1000); //pauses the program for 1 second
			String path = chooseFile.getSelectedFile().getAbsolutePath(); //stores the path of the chosen file
			File file = new File(path); //creates a File object with the chosen path
			ImageIO.write(getBufferedImage(), "png", file); //writes a PNG file to the chosen path
			JOptionPane.showMessageDialog(MainFrame, "Screenshot saved.", "Success", JOptionPane.PLAIN_MESSAGE); //displays a message dialog with a success message
		} 
		catch (Exception e) { //catches any exceptions thrown in the try block
			JOptionPane.showMessageDialog(MainFrame, "Error in saving Photo.", "Error", JOptionPane.ERROR_MESSAGE); //displays a message dialog with an error message				
		}
	}
}


	private void rectangle(int b, int h) {
	
 	Graphics canvas = getGraphicsContext(); //sets the variable canvas equal to a graphics context
 	canvas.setColor(Color.orange); //sets the color of the graphics context to orange
 	if(fill) { //checks to see if the variable fill is true
 		canvas.fillRect(xPos - b/2, yPos, b, h); //if true, fills a rectangle with the specified dimensions
 	} else { //if false, executes the code in the else statement
 		canvas.drawRect(xPos -b/2, yPos, b, h); //draws a rectangle with the specified dimensions
 		forward(0); //moves the turtle forward 0 pixels
 	}
 }

	private void triangle(int side) {
	    for (int i = 0; i < 3; i++) {
	        forward(side);
	        right(120); // turn 120° each time
	    }
	    list.add("triangle " + side);
	    isSaved = false;
	}


	
	private void drawCustomTriangle(int a, int b, int c) {
	    // Draw side a
	    forward(a);

	    // Turn to draw side b
	    int angleB = getAngleFromSides(a, b, c);
	    right(180 - angleB); // internal angle adjustment
	    forward(b);

	    // Turn to draw side c
	    int angleC = getAngleFromSides(b, c, a);
	    right(180 - angleC); // internal angle adjustment
	    forward(c);

	    // Final turn to return to original direction (optional)
	    int angleA = getAngleFromSides(c, a, b);
	    right(180 - angleA);

	    list.add("triangle " + a + " " + b + " " + c);
	    isSaved = false;
	}

	private int getAngleFromSides(int a, int b, int c) {
	    // c is the side opposite to the angle we want
	    double angleRad = Math.acos((a * a + b * b - c * c) / (2.0 * a * b));
	    return (int) Math.toDegrees(angleRad);
	}





	private void randomColor() {
		Random rgb = new Random(); // Create a new instance of the Random class 
		setPenColour(new Color(rgb.nextInt(256),rgb.nextInt(256),rgb.nextInt(256)));  // Set the pen colour to a random colour using random RGB values
	}

	private void square(int side) {
	    for (int i = 0; i < 4; i++) {
	        forward(side);       // move forward
	        right(90);           // turn 90 degrees
	    }
	    list.add("square " + side);
	    isSaved = false;
	}

	private void drawFlag(String country) {
	    Graphics g = getGraphicsContext();
	    clear();  // optional: clear previous drawings
	    reset();  // reset turtle to center

	    switch (country.toLowerCase()) {
	        case "nepal":
	            g.setColor(Color.red);
	            g.fillPolygon(new int[]{xPos, xPos + 50, xPos}, new int[]{yPos, yPos, yPos - 50}, 3);
	            g.fillPolygon(new int[]{xPos, xPos + 50, xPos}, new int[]{yPos, yPos, yPos + 50}, 3);
	            break;

	        case "japan":
	            g.setColor(Color.white);
	            g.fillRect(xPos - 75, yPos - 50, 150, 100);
	            g.setColor(Color.red);
	            g.fillOval(xPos - 25, yPos - 25, 50, 50);
	            break;

	        case "france":
	            g.setColor(Color.blue);
	            g.fillRect(xPos - 75, yPos - 50, 50, 100);
	            g.setColor(Color.white);
	            g.fillRect(xPos - 25, yPos - 50, 50, 100);
	            g.setColor(Color.red);
	            g.fillRect(xPos + 25, yPos - 50, 50, 100);
	            break;

	        case "germany":
	            g.setColor(Color.black);
	            g.fillRect(xPos - 75, yPos - 50, 150, 33);
	            g.setColor(Color.red);
	            g.fillRect(xPos - 75, yPos - 17, 150, 33);
	            g.setColor(Color.yellow);
	            g.fillRect(xPos - 75, yPos + 16, 150, 33);
	            break;

	        default:
	            JOptionPane.showMessageDialog(null, "Unsupported country: " + country);
	    }
	}

	@Override
	public void about() {
	    super.about();  // Do the turtle animation dance
	    Graphics g = getGraphicsContext();
	    
	    // Optional: clear space before writing name
	    g.setColor(Color.white); 
	    g.drawString("Created by Pratik Sharma", 250, 300);  // Append name
	}
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    updateStatusLabel(g);
	}

	
	
	private void help() {
		String detail = "about: Display the turtle dance moving round and the name of the author\n"+
				"penup: Lifts the pen from the canvas, so that movement does not get shown\n"+
				"pendown: Places the pen down on the canvas so movement gets shown as a drawn line\n"+
				"black: Make the pen color black\n"+
				"green: Makes the pen color green\n"+
				"red: Makes the pen color red\n"+
				"white: Makes the pen color white\n"+
				"clear: Clears the whole output screen\n"+
				"reset: Resets the canvas to its initial state with turtle pointing down but does not clear the display\n"+
				"save: It provides options to save command or to save image\n"+
				"load: It provides options to load command or to load image\n"+
				"pencolor <int><int><int>: It takes three diferent color value to make RGB color\n"+
				"penwidth: It takes one parameter and sets the pen stroke\n"+
				"help: Display this menu!\n"+
				"randomcolor: sets the color of the pen to random color\n"+
				"\n"+
				"DRAWINGS"+
				"\n"+
				"circle <radius>: It draws the circle of the radius user enters\n"+
				"rectangle <BREADTH> AND <HEIGHT>: Draws a rectangle\n"+
				"square <SIDE>: Draws a square with the same length of all sides\n"+
				"triangle <int>: Equilateral triangle is drawn\n"+
				"triangle <int><int><int>:Three parameter is passed it draws normal traingle\n"+
				"\n"+
				"LINES BY PASSING PARAMETERS"+
				"\n"+
				"forward <int>: Forwards the turtle by the units passed\n"+
				"backward <int>: Trutle will move backwards by the units passed\n"+
				"turnleft <int>: Turn the turtle to right by degrees passed\n"+
				"turnright <int>: Turn the turtle to left by degree passed\n";


		JOptionPane.showMessageDialog(null, detail);   
		//This code displays a dialog box which contains detailed information about the commands used in the program. 
		
	}
}