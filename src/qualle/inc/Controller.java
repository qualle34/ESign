package qualle.inc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btnRsaDecrypt;
    @FXML
    private Button btnRsaEncrypt;
    @FXML
    private Button btnGamalDecrypt;
    @FXML
    private Button btnGamalEncrypt;


    @FXML
    private TextField inputRsaDecrypt_s;
    @FXML
    private TextField inputRsaDecrypt_e;
    @FXML
    private TextField inputRsaDecrypt_n;
    @FXML
    private TextField inputRsaEncrypt_d;
    @FXML
    private TextField inputRsaEncrypt_m;
    @FXML
    private TextField inputRsaEncrypt_n;

    @FXML
    private TextField inputGamalDecrypt_a;
    @FXML
    private TextField inputGamalDecrypt_b;
    @FXML
    private TextField inputGamalDecrypt_m;
    @FXML
    private TextField inputGamalDecrypt_y;
    @FXML
    private TextField inputGamalEncrypt_m;
    @FXML
    private TextField inputGamalEncrypt_x;
    @FXML
    private TextField inputGamalEncrypt_k;


    @FXML
    private Label labelGamalConst;


    @FXML
    private Label labelRsaDecrypt;
    @FXML
    private Label labelRsaEncrypt;
    @FXML
    private Label labelGamalDecrypt;
    @FXML
    private Label labelGamalEncrypt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int g = 5;
        int p = 23;
        labelGamalConst.setText("g= " + g + "   p= " + p);

        btnRsaDecrypt.setOnAction(event -> {

            int s = Integer.parseInt(inputRsaDecrypt_s.getText());
            int e = Integer.parseInt(inputRsaDecrypt_e.getText());
            int n = Integer.parseInt(inputRsaDecrypt_n.getText());

            int m = Power(s, e, n);

            labelRsaDecrypt.setText("m= " + m);
        });

        btnRsaEncrypt.setOnAction(event -> {

            int d = Integer.parseInt(inputRsaEncrypt_d.getText());
            int m = Integer.parseInt(inputRsaEncrypt_m.getText());
            int n = Integer.parseInt(inputRsaEncrypt_n.getText());

         int s = Power(m, d, n);

            labelRsaEncrypt.setText("s= " + s);

        });

        btnGamalDecrypt.setOnAction(event -> {

            int a = Integer.parseInt(inputGamalDecrypt_a.getText());
            int b = Integer.parseInt(inputGamalDecrypt_b.getText());
            int m = Integer.parseInt(inputGamalDecrypt_m.getText());
            int y = Integer.parseInt(inputGamalDecrypt_y.getText());


            BigInteger power = BigInteger.valueOf(y).pow(a); // y^a * a^b mod p
            BigInteger power2 = BigInteger.valueOf(a).pow(b);
            BigInteger mul =  power.multiply(power2);
            BigInteger mod = BigInteger.valueOf(p);
            BigInteger result = mul.mod(mod);

            int one = result.intValue();
            int two = Power(g, m, p);


            labelGamalDecrypt.setText("y^a * a^b mod p = " + one + "   g^m mod p = " + two + "\n" + power + "  " + power2 + mul);
        });

        btnGamalEncrypt.setOnAction(event -> {

            int m = Integer.parseInt(inputGamalEncrypt_m.getText());
            int k = Integer.parseInt(inputGamalEncrypt_k.getText());
            int x = Integer.parseInt(inputGamalEncrypt_x.getText());

            int y = Power(g, x, p);
            int a = Power(g, k, p);

            int b = Power2(y, k, m, p);

            labelGamalEncrypt.setText("a= " + a + "   b= " + b + "   y= " + y);
        });
    }

    private int Power(int a, int b, int c) { // a^b mod c

        BigInteger power = BigInteger.valueOf(a).pow(b);
        BigInteger mod = BigInteger.valueOf(c);
        BigInteger result = power.mod(mod);

        return result.intValue();
    }

    private int Power2(int a, int b, int m, int c) { // a^b * m mod c

        BigInteger power = BigInteger.valueOf(a).pow(b);
        BigInteger mul = BigInteger.valueOf(m);
        BigInteger mod = BigInteger.valueOf(c);

        BigInteger buffer = power.multiply(mul);
        BigInteger result = buffer.mod(mod);

        return result.intValue();
    }
}
