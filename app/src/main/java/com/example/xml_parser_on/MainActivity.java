package com.example.xml_parser_on;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
TextView tv1;
TextView name[];
    TextView website[];
    TextView category[];
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView1);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(1);

        try {

            URL url = new URL("http://www.androidpeople.com/wp-content/uploads/2010/06/example.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("item");

            /** Assign textview array lenght by arraylist size */
            name = new TextView[nodeList.getLength()];
            website = new TextView[nodeList.getLength()];
            category = new TextView[nodeList.getLength()];

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                name[i] = new TextView(this);
                website[i] = new TextView(this);
                category[i] = new TextView(this);

                Element fstElmnt = (Element) node;
                NodeList nameList = fstElmnt.getElementsByTagName("name");
                Element nameElement = (Element) nameList.item(0);
                nameList = nameElement.getChildNodes();
                name[i].setText("Name = " + ((Node) nameList.item(0)).getNodeValue());

                NodeList websiteList = fstElmnt.getElementsByTagName("website");
                Element websiteElement = (Element) websiteList.item(0);
                websiteList = websiteElement.getChildNodes();
                website[i].setText("Website = " + ((Node) websiteList.item(0)).getNodeValue());

                category[i].setText("Website Category = " + websiteElement.getAttribute("category"));

                layout.addView(name[i]);
                layout.addView(website[i]);
                layout.addView(category[i]);
            }
        } catch (Exception e) {
            System.out.println("XML Pasing Excpetion = " + e);
        }

        /** Set the layout view to display */
        setContentView(layout);

    }
}



