package com.gc.x05_pullxml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.gc.x05_pullxml.message.Message;

import android.os.Bundle;
import android.app.Activity;
import android.util.Xml;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	List<Message> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    
    public void click(View v){
    	//��src���õ�sms.xml�ļ�
    	InputStream is = getClassLoader().getResourceAsStream("sms.xml");
    	//��pull����xml�ļ�
    	XmlPullParser xp = Xml.newPullParser();
    	//��ʼ��
    	try {
			xp.setInput(is, "utf-8");
			//��õ�ǰ�ڵ��¼�����
			int type = xp.getEventType();
			Message mes = null;
			while(type!=XmlPullParser.END_DOCUMENT){			
				switch(type){
				//��ʼ��ǩ
				case XmlPullParser.START_TAG:
					if("message".equals(xp.getName())){
						list = new ArrayList<Message>();
					}else if("sms".equals(xp.getName())){
						mes = new Message();
					}else if("address".equals(xp.getName())){
						mes.setAddress(xp.nextText());
					}else if("date".equals(xp.getName())){
						mes.setDate(xp.nextText());
					}else if("type".equals(xp.getName())){
						mes.setType(xp.nextText());
					}else if("body".equals(xp.getName())){
						mes.setBody(xp.nextText());
					}
					break;
				//������ǩ
				case XmlPullParser.END_TAG:
					if("sms".equals(xp.getName())){
						list.add(mes);
					}
					break;
				}
				//type���������ƶ�
				type = xp.next();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
