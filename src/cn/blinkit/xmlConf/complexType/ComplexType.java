package cn.blinkit.xmlConf.complexType;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Only-lezi on 2016/12/30.
 */
public class ComplexType {

    private String[] arrs;
    private List<String> list;
    private Map<String, String> map;
    private Properties properties;

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void print() {
        System.out.println("arrs: " + arrs.toString());
        System.out.println("list: " + list);
        System.out.println("map: " + map);
        System.out.println("properties: " + properties);
    }
}
