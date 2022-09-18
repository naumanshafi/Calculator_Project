package Calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Controller {
    
    stringProcessingClass driver = new stringProcessingClass();
    public static boolean equalFlag = false;
    public static boolean checkExpressionExecuteFlag = false;
    @FXML
    private Text output;
    @FXML
    private Text smallText;

    private boolean start = true;


    @FXML
    private void processNumpad(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }
        
        String value = ((Button)event.getSource()).getText();
        if(Controller.equalFlag == true)
        {
            smallText.setText("");
            output.setText("");
        }
        if (!"=".equals(value)) {
            Controller.equalFlag = false;
        }
        output.setText(output.getText() + value);
    }

    @FXML
    private void squareFunction(ActionEvent event) {   
        String value = ((Button)event.getSource()).getText();
        String stringSqure = output.getText();
        if(!output.getText().isEmpty())
        {
            double doubleSqure=Double.valueOf(stringSqure); 
            doubleSqure = doubleSqure * doubleSqure;
            String s=String.valueOf(doubleSqure);
            output.setText(s);
        }
    }
    
    @FXML
    private void squreRootFunction(ActionEvent event) {   
        String value = ((Button)event.getSource()).getText();
        String stringSqure = output.getText();
        if(!output.getText().isEmpty())
        {
            double doubleRoot=Double.valueOf(stringSqure); 
            doubleRoot = Math.sqrt(doubleRoot);
            String s=String.format("%.3f",doubleRoot);
            output.setText(s);
        }
    }
    
    @FXML
    private void reverseFunction(ActionEvent event) {   
        String value = ((Button)event.getSource()).getText();
        String stringSqure = output.getText();
        if(!output.getText().isEmpty())
        {
            double doubleReverse=Double.valueOf(stringSqure); 
            doubleReverse = 1.0/doubleReverse;
            String s=String.format("%.2f",doubleReverse);
            output.setText(s);
        }
    }
    
    @FXML
    private void backSpace(ActionEvent event) {   
        Controller.equalFlag = false;
        output.setText("");
        String value = ((Button)event.getSource()).getText();
        String stringSqure = output.getText();
        if(!smallText.getText().isEmpty())
        {
            String str = smallText.getText();
            str = str.substring(0, str.length() - 1);
            smallText.setText(str);
        }
    }
    
    @FXML
    private void processOperator(ActionEvent event) throws ScriptException {
        String value = ((Button)event.getSource()).getText();
        if(Controller.equalFlag == true)
        {
            smallText.setText("");
        }
        if (!"=".equals(value)) {
            Controller.equalFlag = false;
            if ("CE".equals(value)) {
                output.setText("");
            }
            else if ("C".equals(value)) {
                smallText.setText("");
                output.setText("");
            }else if ("+/-".equals(value)){
                String hString = output.getText();
                if(hString.length()!=0)
                {
                    Double hLong = Double.parseDouble(hString);
                    if(hLong<0){
                        hLong = Math.abs(hLong);
                    } else if(hLong>0){
                        hLong = 0-hLong;
                    }
                    output.setText(Double.toString(hLong));
                }
            } else if ("×".equals(value)){
                smallText.setText(smallText.getText() + output.getText() + "*");
                output.setText("");
            } else if ("÷".equals(value)){
                smallText.setText(smallText.getText() + output.getText() + "/");
                output.setText("");


            } else {
                smallText.setText(smallText.getText() + output.getText() + value);
                output.setText("");
            }
        } else if ("=".equals(value) && Controller.equalFlag == false){
            Controller.equalFlag = true;
            smallText.setText(smallText.getText()+output.getText());
            try {
                String inputOnCalculator = smallText.getText();
                BigDecimal holderOutput = new BigDecimal(driver.calculationOfExpression(inputOnCalculator));
                holderOutput = holderOutput.setScale(1, RoundingMode.HALF_UP);

                output.setText(holderOutput.toString());
            } catch (Exception e){
                output.setText("Syntax Error");
            }

        }
    }
}
