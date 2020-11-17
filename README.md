iOS:[https://github.com/QiaokeZ/iOS_AnyObjectMapper](https://github.com/QiaokeZ/iOS_AnyObjectMapper)

项目中有很多JavaBean类，少则5-10个，多则10-20个，其中大部分的字段都重复了，并且有些字段名称一样但类型不一样，导致不能重用，为了解决这些问题，写了个智能JSON解析，一个JavaBean类替代所有的类。



//将JSON有可能出现的key全部放进来
//不确定的类型用AnyObject
```java
public class PublicBean {
    public String price;
    public String version;
    public String name;
    public AnyObject ID;
    public AnyObject desc;
    public AnyObject cpu;
    public AnyObject memory;
    public AnyObject graphics;
    public AnyObject mainboard;
    public AnyObject radiator;
    public AnyObject hardDisk;
    public AnyObject networkAdapter;
    public AnyObject monitor;
    public AnyObject keyboard;
    public AnyObject mouse;
    public AnyObject systems;
    public AnyObject codeName;
    public AnyObject _package;
    public AnyObject technology;
    public AnyObject coreVID;
    public AnyObject coreSpeed;
    public AnyObject multiplier;
    public AnyObject busSpeed;
    public AnyObject ratedFSB;
    public AnyObject cores;
    public AnyObject threads;
    public AnyObject type;
    public AnyObject size;
    public AnyObject dramFrequency;
    public AnyObject gpu;
    public AnyObject coolingMode;
    public AnyObject series;
    public AnyObject model;
    public AnyObject _interface;
    public AnyObject resolution;
}

public static void testJSONToBean() {
     String json = json = "{\n" +
             "    \"cpu\": {\n" +
             "        \"name\": \"Intel Core i9-9900K\",\n" +
             "        \"codeName\": \"kaby Lake\",\n" +
             "        \"package\": \"Socket 1151 LGA\",\n" +
             "        \"technology\": \"14nm\",\n" +
             "        \"coreVID\": \"0.944V\",\n" +
             "        \"coreSpeed\": \"5000.00 MHz\",\n" +
             "        \"multiplier\": \"x 50.0(8 - 50)\",\n" +
             "        \"busSpeed\": \"100.00 MHz\",\n" +
             "        \"ratedFSB\": \"\",\n" +
             "        \"cores\": 8,\n" +
             "        \"threads\": 16,\n" +
             "        \"price\": 3949\n" +
             "    },\n" +
             "    \"memory\": {\n" +
             "        \"name\": \"金士顿(Kingston)\",\n" +
             "        \"type\": \"288-Pin DDR4 UDIMM\",\n" +
             "        \"size\": \"64GB\",\n" +
             "        \"dramFrequency\": \"DDR4 3200\",\n" +
             "        \"price\": 2499\n" +
             "    },\n" +
             "    \"graphics\": {\n" +
             "        \"name\": \"蓝宝石 SAPPHIRE RX 5700 XT\",\n" +
             "        \"gpu\": \"AMD\",\n" +
             "        \"technology\": \"7nm\",\n" +
             "        \"price\": 3199\n" +
             "    },\n" +
             "    \"mainboard\": {\n" +
             "        \"name\": \"技嘉 GIGABYTE\",\n" +
             "        \"type\": \"Intel平台\",\n" +
             "        \"model\": \"Z390 GAMING X\",\n" +
             "        \"price\": 1298\n" +
             "    },\n" +
             "    \"radiator\": {\n" +
             "        \"name\": \"猫头鹰\",\n" +
             "        \"coolingMode\": \"风冷\",\n" +
             "        \"price\": 699\n" +
             "    },\n" +
             "    \"hardDisk\": {\n" +
             "        \"name\": \"三星（SAMSUNG）\",\n" +
             "        \"series\": \"970 EVO 系列\",\n" +
             "        \"model\": \"MZ-V7E500BW\",\n" +
             "        \"size\": \"1T\",\n" +
             "        \"price\": 1399\n" +
             "    },\n" +
             "    \"networkAdapter\": {\n" +
             "        \"name\": \"奋威（fenvi）FV-T919\",\n" +
             "        \"type\": \"双频网卡\",\n" +
             "        \"model\": \"FV-HB1200\",\n" +
             "        \"interface\": \"PCI-E接口\",\n" +
             "        \"price\": 238\n" +
             "    },\n" +
             "    \"monitor\": {\n" +
             "        \"name\": \"戴尔 DELL\",\n" +
             "        \"model\": \"U2720QM\",\n" +
             "        \"resolution\": \"3850x2160\",\n" +
             "        \"price\": 4199\n" +
             "    },\n" +
             "    \"keyboard\": {\n" +
             "        \"name\": \"Apple\",\n" +
             "        \"type\": \"无线键盘\",\n" +
             "        \"price\": 699\n" +
             "    },\n" +
             "    \"mouse\": {\n" +
             "        \"name\": \"Apple\",\n" +
             "        \"type\": \"无线键盘\",\n" +
             "        \"price\": 539\n" +
             "    },\n" +
             "    \"systems\": [\n" +
             "        {\n" +
             "            \"name\": \"macOS\",\n" +
             "            \"version\": \"10.15.4\"\n" +
             "        },\n" +
             "        {\n" +
             "            \"name\": \"windows8\",\n" +
             "            \"version\": \"8\"\n" +
             "        },\n" +
             "        {\n" +
             "            \"name\": \"windows10\",\n" +
             "            \"version\": \"10\"\n" +
             "        },\n" +
             "        {\n" +
             "            \"name\": \"CentOS\",\n" +
             "            \"version\": \"6.5\"\n" +
             "        },\n" +
             "        {\n" +
             "            \"name\": \"Ubuntu\",\n" +
             "            \"version\": \"4.8.2\"\n" +
             "        }\n" +
             "    ],\n" +
             "    \"name\": \"顶级硬件配置\",\n" +
             "    \"id\": \"EB17AA00-51C4-55BC-B863-8040ADA8DJ46\",\n" +
             "    \"desciption\": \"CPU:i9-9900K, 内存条:金士顿64GB, 显卡:蓝宝石RX5700XT\\n主板:技嘉Z390, 硬盘:三星970EVO 1T, 散热器:猫头鹰\"\n" +
             "}";

     Map<String, String> decodeCodingkeys = new HashMap();
     decodeCodingkeys.put("_package", "package");

     Serializer serializer = new Serializer();
     serializer.setDecodeCodingkeys(decodeCodingkeys);

     //公共bean(PublicBean)的优缺点:
     //优点：整个项目只需要一个bean
     //缺点：取值时比较麻烦
     PublicBean publicBean = Mapper.decode(PublicBean.class, json, serializer);
     System.out.println(publicBean.name); //顶级硬件配置
     System.out.println(publicBean.cpu.get(PublicBean.class).name); //Intel Core i9-9900K
     System.out.println(publicBean.cpu.get(PublicBean.class).codeName.get(String.class)); //kaby Lake
 }
 ```