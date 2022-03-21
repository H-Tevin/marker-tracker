import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Interface {
    public static JFrame main = new JFrame("Expo Tracker");
    static ArrayList<Integer> list = new ArrayList<>();
    static Color inputtedColor;
    static String inputtedBrand;
    static int markerCount = 0;
    static Border redline = BorderFactory.createLineBorder(Color.red);
    static JPanel panel = new JPanel();
    static GridLayout layout = new GridLayout(1, 4);

    public static void launch() throws FileNotFoundException {
        panel.setSize(1920, 1080);
        File file = new File("calculator.txt");
        Scanner in = new Scanner(file);

/*        while(in.hasNextLine())
        {
            Color c = null;
            String b = "";
            float m = 0;
            while(!in.equals("\n"))
            {
                c = stringToColor(in.nextLine());
            }
            while(!in.equals("\n"))
            {
                b = in.nextLine();
            }
            while(!in.equals("\n"))
            {
                m = in.nextFloat();
                in.nextLine();
            }

            markerPanel(c, b, m);
            if(m < .1)
            {
                refill();
            }
        }*/
        JLabel nothing = new JLabel("                                                                                                                                                                                                                                                                               ");
        JPanel blank = new JPanel();
        blank.add(nothing);
        blank.setSize(470, 250);
        JPanel button = new JPanel();
        button.setSize(470, 250);
        JButton boughtMarker = new JButton("I Bought a New Pen!");
        boughtMarker.setPreferredSize(new Dimension(470, 40));
        button.add(boughtMarker);
        panel.add(button);
        panel.add(blank);
        main.getContentPane().add(panel);
        main.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        main.setVisible(true);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boughtMarker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newMarker();
            }
        });
    }

    public static void newMarker() {
        JFrame newMarker = new JFrame("New Marker");
        JPanel panel = new JPanel();
        JTextField colorInput = new JTextField("Enter Color");
        colorInput.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField brandInput = new JTextField("Enter Brand");
        brandInput.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel date = new JLabel("The Current Date is " + java.time.LocalDate.now());
        date.setHorizontalAlignment(SwingConstants.CENTER);

        JButton more = new JButton("Add One More");
        more.setPreferredSize(new Dimension(300, 40));
        panel.add(more);
        JButton done = new JButton("Done");
        more.setPreferredSize(new Dimension(300, 40));
        panel.add(done);
        colorInput.setPreferredSize(new Dimension(300, 40));
        panel.add(colorInput);
        brandInput.setPreferredSize(new Dimension(300, 40));
        panel.add(brandInput);
        date.setPreferredSize(new Dimension(300, 40));
        panel.add(date);
        newMarker.getContentPane().add(panel);
        newMarker.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        main.setVisible(false);
        newMarker.setVisible(true);
        newMarker.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag1 = false;
                boolean flag2 = false;
                newMarker.setVisible(false);
                main.setVisible(true);
                if(!(colorInput.getText().equals("Enter Color")))
                {
                    inputtedColor = stringToColor(colorInput.getText().toLowerCase());
                    flag1 = true;
                }
                if(!(brandInput.getText().equals("Enter Brand")))
                {
                    inputtedBrand = brandInput.getText();
                    flag2 = true;
                }
                if(flag1 && flag2)
                {
                    markerCount ++;
                    Main.inventory.add(new Marker(brandInput.getText(), colorInput.getText()));
                    markerPanel(inputtedColor, inputtedBrand, Main.inventory.get(Main.inventory.size() - 1).getLifeLength());
                }



                PrintWriter writer = null;
                PrintWriter calculator = null;
                try {
                    writer = new PrintWriter(new FileWriter("allMarkers.txt", true));
                    calculator = new PrintWriter(new FileWriter("calculator.txt", true));
                } catch (IOException d) {
                    d.printStackTrace();
                }

                for(int i = 0; i < Main.inventory.size(); i++)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Calendar c = Calendar.getInstance();
                    c.setTime(Main.inventory.get(i).getDate()); // Using today's date
                    c.add(Calendar.DATE, 913); // Adding 2.5 years
                    String output = sdf.format(c.getTime());

                    writer.println("Brand: " + Main.inventory.get(i).getBrand() + "\nColor: " +
                            Main.inventory.get(i).getColor() + "\nDate Purchased: " + Main.inventory.get(i).getDate()
                            + "\nExpected Death: " + output + "\n");

                    calculator.println(Main.inventory.get(i).getBrand() + "\n" + Main.inventory.get(i).getColor() + "\n"
                            + Main.inventory.get(i).getLifeLength());
                }
                writer.close();
                calculator.close();
            }
        });
        more.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag1 = false;
                boolean flag2 = false;
                if(!(colorInput.getText().equals("Enter Color")))
                {
                    inputtedColor = stringToColor(colorInput.getText().toLowerCase());
                    flag1 = true;
                }
                if(!(brandInput.getText().equals("Enter Brand")))
                {
                    inputtedBrand = brandInput.getText();
                    flag2 = true;
                }
                if(flag1 && flag2)
                {
                    markerCount ++;
                    Main.inventory.add(new Marker(brandInput.getText(), colorInput.getText()));
                    markerPanel(inputtedColor, inputtedBrand, Main.inventory.get(Main.inventory.size() - 1).getLifeLength());
                    colorInput.setText("Enter Color");
                    brandInput.setText("Enter Brand");
                }
            }
        });
        colorInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (colorInput.getText().equals("Enter Color")) {
                    colorInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (colorInput.getText().length() == 0) {
                    colorInput.setText("Enter Color");
                }

            }
        });
        brandInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (brandInput.getText().equals("Enter Brand")) {
                    brandInput.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (brandInput.getText().length() == 0) {
                    brandInput.setText("Enter Brand");
                }

            }
        });
    }

    public static void markerPanel(Color inputtedColor, String inputtedBrand, float multiplier)
    {
        BarChart chart = null;
        Color color = inputtedColor;
        String brand = inputtedBrand;
        JPanel markerPanel = new JPanel();
        markerPanel.setSize(550, 250);
        markerPanel.setBorder(redline);
        JLabel number = new JLabel("Marker Number: " + markerCount);
        chart = new BarChart(list, "Marker Number " + markerCount, "Percentage", color, multiplier);
        markerPanel.add(number);
        markerPanel.add(chart);
        chart.setPreferredSize(new Dimension(100, 230));
        if(markerCount%3 != 0)
        {
            layout.setColumns((markerCount/3)+2);
        }
        else
        {
            layout.setColumns((markerCount/3)+1);
        }
        panel.add(markerPanel);
    }

    public static void refill()
    {
        JFrame refill = new JFrame();
        JPanel panel = new JPanel();
        JLabel warning = new JLabel("One of more of your markers is about to die!\nOrder more here:\n");
        panel.add(warning);
        refill.setVisible(true);

    }

    public static Color stringToColor(final String value) {
        if (value == null) {
            return Color.black;
        }
        try {
            // get color by hex or octal value
            return Color.decode(value);
        } catch (NumberFormatException nfe) {
            // if we can't decode lets try to get it by name
            try {
                // try to get a color by name using reflection
                final Field f = Color.class.getField(value);

                return (Color) f.get(null);
            } catch (Exception ce) {
                // if we can't get any color return black
                return Color.black;
            }
        }
    }
}
