/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NotePad_JavaFX;

import java.io.*;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Notepad and simple Compiler with Javafx
 *
 * @author Abdullah Hanfy Date:- 15 DEC,021
 */
public class NotePad_JavaFX extends Application {

    /*Main Pane*/
    VBox root_pane;

    /*Pane to contain Image and text as signeture of the app*/
    Label footer_pane;

    /*The bar to hold all options of the note pad*/
    MenuBar mBAr;

    /*Menus Included in The Bar*/
    Menu file;
    Menu edit;
    Menu compiler;
    Menu help;

    /*File menu items*/
    MenuItem f_new;
    MenuItem f_open;
    MenuItem f_save;
    MenuItem f_exit;
    SeparatorMenuItem f_sep;

    /*Edit menu items*/
    MenuItem e_undo;
    MenuItem e_cut;
    MenuItem e_copy;
    MenuItem e_paste;
    MenuItem e_delete;
    MenuItem e_selectAll;
    SeparatorMenuItem e1_sep;
    SeparatorMenuItem e2_sep;

    /*Help Menu Items*/
    MenuItem c_c;
    MenuItem c_java;
    /*Help Menu Items*/
    MenuItem h_about;


    /*Text Area of NotePad*/
    Label ipLabel;
    TextArea inputArea;

    Label opLabel;
    TextArea outputArea;

    /*Image and Text as copyright*/
    Image img;
    ImageView view;
    Text txt;

    /*About Dialog*/
    Dialog about;
    ButtonType about_type;

    /*open and Save Dialog*/
    FileChooser fileChooser;
    FileChooser fileSaver;

    /*Alert Dialog*/
    Alert alert;
    Optional<ButtonType> result;

    /*read and save Files*/
    File fileRead;
    FileReader fileReader;
    File fileSave;
    FileWriter fileWriter;

    /*to check change in text area*/
    int size;

    /*----------------------------------*/
    @Override
    public void init() throws Exception {
        /*initiate The root pane*/ 
        root_pane = new VBox();
                
        /*Initiate MenuBar*/
        mBAr = new MenuBar();
        
        /*Initiate 3 Menus*/
        file = new Menu("File");
        edit = new Menu("Edit");
        compiler = new Menu("Compiler");
        help = new Menu("Help");

        /*Initate All Items for the File Menu ===> Names and shortCuts*/
        f_new = new MenuItem("New");
        f_new.setAccelerator(KeyCombination.keyCombination("Ctrl+q"));
        f_open = new MenuItem("Open...");
        f_open.setAccelerator(KeyCombination.keyCombination("Ctrl+w"));
        f_save = new MenuItem("Save");
        f_save.setAccelerator(KeyCombination.keyCombination("Ctrl+e"));
        f_exit = new MenuItem("Exit");
        f_exit.setAccelerator(KeyCombination.keyCombination("Ctrl+r"));
        f_sep = new SeparatorMenuItem();

        /*Initate All Items for the Edit Menu ===> Names and shortCuts*/
        e_undo = new MenuItem("Undo");
        e_undo.setAccelerator(KeyCombination.keyCombination("Ctrl+t"));
        e_cut = new MenuItem("Cut");
        e_cut.setAccelerator(KeyCombination.keyCombination("Ctrl+y"));
        e_copy = new MenuItem("Copy");
        e_copy.setAccelerator(KeyCombination.keyCombination("Ctrl+u"));
        e_paste = new MenuItem("Paste");
        e_paste.setAccelerator(KeyCombination.keyCombination("Ctrl+i"));
        e_delete = new MenuItem("Delete");
        e_delete.setAccelerator(KeyCombination.keyCombination("Ctrl+o"));
        e_selectAll = new MenuItem("Select All");
        e_selectAll.setAccelerator(KeyCombination.keyCombination("Ctrl+p"));
        e1_sep = new SeparatorMenuItem();
        e2_sep = new SeparatorMenuItem();

        /*Initate All Items for the Compiler Menu ===> Names and shortCuts*/
        c_c = new MenuItem("C");
        c_c.setAccelerator(KeyCombination.keyCombination("Ctrl+shift+c"));
        c_java = new MenuItem("Java");
        c_java.setAccelerator(KeyCombination.keyCombination("Ctrl+shift+j"));

        /*Initate All Items for the Help Menu ===> Names and shortCuts*/
        h_about = new MenuItem("About");
        h_about.setAccelerator(KeyCombination.keyCombination("Ctrl+shift+a"));

        /*initial input  Text areas  and It's label of prgram*/
        ipLabel = new Label("Input Area");
        ipLabel.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(ipLabel, 0.0);
        AnchorPane.setRightAnchor(ipLabel, 0.0);
        ipLabel.setAlignment(Pos.CENTER);
        
        inputArea = new TextArea();
        inputArea.setPrefHeight(300); //sets height of the TextArea to 400 pixels.
        inputArea.setPrefWidth(300); //sets width of the TextArea to 300 pixels.

        /*initial output  Text areas  and It's label of prgram*/

        opLabel = new Label("Output Area");
        opLabel.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(opLabel, 0.0);
        AnchorPane.setRightAnchor(opLabel, 0.0);
        opLabel.setAlignment(Pos.CENTER);

        outputArea = new TextArea();
        outputArea.setPrefHeight(300); //sets height of the TextArea to 400 pixels.
        outputArea.setPrefWidth(300); //sets width of the TextArea to 300 pixels.
        outputArea.setEditable(false); //only to show output of compiler

        /*add all items in File Menu*/
        file.getItems().addAll(f_new, f_open, f_save, f_sep, f_exit);
        /*add all items in Edit Menu*/
        edit.getItems().addAll(e_undo, e1_sep, e_cut, e_copy, e_paste, e_delete, e2_sep, e_selectAll);
        /*add all items in Compiler Menu*/
        compiler.getItems().addAll(c_c, c_java);
        /*add all items in Help Menu*/
        help.getItems().addAll(h_about);

        /*Initiate Image and Text as copyRigths and it's pane as label*/
        img = new Image(new FileInputStream("D:\\ES_ITI\\ITI_ES_42_Materials\\05_JAVA\\LABS\\NotePad_JavaFX\\src\\image\\myPic.jpg"));
        view = new ImageView(img);
        txt = new Text("Abdullah Hanfy");
        
        footer_pane = new Label("Abdullah Hanfy",view);
        footer_pane.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(footer_pane, 0.0);
        AnchorPane.setRightAnchor(footer_pane, 0.0);
        footer_pane.setAlignment(Pos.BOTTOM_RIGHT);
        footer_pane.setContentDisplay(ContentDisplay.RIGHT);
        footer_pane.setEffect(new DropShadow(2.0, Color.BLUE));
        

        //Creating a File chooser
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open NotePad");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));

        //Creating a File Saver
        fileSaver = new FileChooser();
        fileSaver.setTitle("Save");
        fileSaver.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));

        /*add All Menus to MenuBar*/
        mBAr.getMenus().addAll(file, edit, compiler, help);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        /*-----------File Menue Items-------------------------------*/
        /*New MenuItem Event Handler*/
        f_new.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            int new_size = inputArea.getLength();
                       
            if (new_size != size) {
                /*Here user has changed the text area and should ask to save firstly*/

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Save Confirmation");
                alert.setHeaderText("Save");
                alert.setContentText("Do you want to save firstly?");
                result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    System.out.println("Save fistly");
                    f_save.fire();
                    inputArea.clear();

                } else {
                    /*No changes had done*/
                    inputArea.clear();
                }

            } else {
                inputArea.clear();
            }
            outputArea.clear();

        });
        /*open MenuItem Event Handler*/
        f_open.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            /*Create obj from File Class and open the open window*/
            File fileChoice = fileChooser.showOpenDialog(root_pane.getScene().getWindow());
            if (fileChoice == null) {
                return;
            } else {
                String path = fileChoice.getPath();

                fileRead = new File(path);
                /*Get the length of the file*/
                int len = (int) fileRead.length();
                /*Make array of char to get the content of file want to open in NotePad*/
                char[] c = new char[len];
                try {
                    /*create obj from FileReader Class*/
                    fileReader = new FileReader(fileRead);
                    /*Read all content of file in the array c*/
                    int i = fileReader.read(c);
                    /*create String from the array of char*/
                    String str = new String(c);
                    /*set the content in Text area*/
                    inputArea.setText(str);
                    /*close The file*/
                    fileReader.close();
                    size = inputArea.getLength();
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }
            }
            outputArea.clear();
        });
        /*Save MenuItem Event Handler*/
        f_save.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            File fileChoice = fileChooser.showSaveDialog(root_pane.getScene().getWindow());
            if (fileChoice == null) {
                return;
            } else {
                String path = fileChoice.getPath();
            fileSave = new File(path);

            try {
                fileWriter = new FileWriter(fileSave);
                String str = inputArea.getText();
                fileWriter.write(str);
                fileWriter.close();
            } catch (IOException ex) {
                //Logger.getLogger(NotePad_JavaFX.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            outputArea.clear();
        });
        /*Exit MenuItem Event Handler*/
        f_exit.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            /*Close The Stage*/
            //primaryStage.close();
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Exit");
            alert.setContentText("Are you sure to Exit?");
            result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                primaryStage.close();
            } else {

            }
        });
        /*------------------------------------------------------------------*/

        /*--------------------------Edit Menu-------------------------------*/
        e_undo.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            inputArea.undo();
        });
        e_cut.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            inputArea.cut();
        });
        e_copy.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            inputArea.copy();
        });
        e_paste.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            inputArea.paste();
        });
        e_delete.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            inputArea.deleteText(inputArea.getSelection());
        });
        e_selectAll.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            inputArea.selectAll();
        });

        /*------------------------------------------------------------------*/
        /*--------------------------Compile Menu-------------------------------*/
        
        c_c.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                FileWriter writer;
                try {
                    writer = new FileWriter("Main.c");
                    BufferedWriter buf = new BufferedWriter(writer);
                    buf.write(inputArea.getText());
                    buf.close();
                    writer.close();
                } catch (IOException ex) {
                   
                }
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c","gcc Main.c && a");
            builder.redirectErrorStream(true);
            Process p;
            try {
                p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) { break; }
                    
                    outputArea.setText(line);
                    
                }
                } catch (IOException ex) {
                   
                }
            }
        
        
        });
        
        c_java.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                FileWriter writer;
                try {
                    writer = new FileWriter("Main.java");
                    BufferedWriter buf = new BufferedWriter(writer);
                    buf.write(inputArea.getText());
                    buf.close();
                    writer.close();
                } catch (IOException ex) {
                   
                }
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c","javac Main.java && java Main");
            builder.redirectErrorStream(true);
            Process p;
            try {
                p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) { break; }
                    
                    outputArea.setText(line);
                    
                }
                } catch (IOException ex) {
                   
                }
            }
        
        
        });
       
        
        /*--------------------------About Dialog----------------------------*/
        /*About Dialogue*/
        //Creating a dialog
        about = new Dialog();
        //Setting the title
        about.setTitle("Dialog");
        //Setting the content of the dialog
        about.setContentText("V0.0 of Abdullah Hanfy NotePad");
        about_type = new ButtonType("Ok", ButtonData.OK_DONE);
        //Adding buttons to the dialog pane
        about.getDialogPane().getButtonTypes().add(about_type);
        h_about.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            about.showAndWait();
        });
        /*------------------------------------------------------------------*/


        /*------------------------------------------------------------------*/
        size = inputArea.getLength();

        //Setting the space between the nodes of a VBox pane 
        root_pane.setSpacing(5);

        root_pane.getChildren().addAll(mBAr, ipLabel, inputArea, opLabel, outputArea, footer_pane);


        Scene scene = new Scene(root_pane, 600, 600);
        primaryStage.setTitle("Area51 Compiler");
        primaryStage.getIcons().add(new Image(new FileInputStream("D:\\ES_ITI\\ITI_ES_42_Materials\\05_JAVA\\LABS\\NotePad_JavaFX\\src\\image\\ghost.PNG")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
