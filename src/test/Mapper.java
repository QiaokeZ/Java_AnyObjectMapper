package test;

import core.extension.ObjectMapper;
import core.extension.ObjectSerializer;
import core.org.json.JSONArray;
import core.org.json.JSONObject;
import test.bean.Computer;
import test.bean.PublicBean;
import test.bean._System;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper {

    private static ObjectSerializer serializer;

    public static void main(String[] args) {
        Map<String, String> decodeCodingkeys = new HashMap();
        decodeCodingkeys.put("desc", "desciption");
        decodeCodingkeys.put("ID", "id");
        decodeCodingkeys.put("_interface", "interface");
        decodeCodingkeys.put("_package", "package");

        Map<String, Class> decodeClassInArrayKeys = new HashMap();
        decodeClassInArrayKeys.put("systems", _System.class);


        //公共bean(PublicBean)的优缺点:
        //缺点：取值时比较麻烦
        //优点：整个项目只需要一个bean

        serializer = new ObjectSerializer();
        serializer.set(ObjectSerializer.DecodeCodingkeys, decodeCodingkeys);
        serializer.set(ObjectSerializer.DecodeClassInArrayKeys, decodeClassInArrayKeys);

        jsonToBean();
        mapToBean();
        jsonArrayToBean();
        listToBean();
    }

    public static void jsonToBean() {
        try {
            Computer computer = ObjectMapper.decode(Computer.class, json, serializer);
            PublicBean publicBean = ObjectMapper.decode(PublicBean.class, json, serializer);
            System.out.println(computer.cpu.name);
            System.out.println(computer.cpu.codeName);
            System.out.println(publicBean.cpu.get(PublicBean.class).name);
            System.out.println(publicBean.cpu.get(PublicBean.class).codeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mapToBean() {
        JSONObject jsonObject = new JSONObject(json);
        Map<String, Object> map = jsonObject.toMap();
        try {
            Computer computer = ObjectMapper.decode(Computer.class, map, serializer);
            PublicBean publicBean = ObjectMapper.decode(PublicBean.class, map, serializer);
            System.out.println(computer.cpu.name);
            System.out.println(publicBean.cpu.get(PublicBean.class).name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jsonArrayToBean() {
        try {
            List<Computer> computers = ObjectMapper.decode(Computer.class, jsonArray, serializer);
            List<PublicBean> publicBeans = ObjectMapper.decode(PublicBean.class, jsonArray, serializer);
            System.out.println(computers.get(1).cpu.name);
            System.out.println(publicBeans.get(2).cpu.get(PublicBean.class).name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listToBean() {
        JSONArray jsonObject = new JSONArray(jsonArray);
        List<Object> list = jsonObject.toList();
        try {
            List<Computer> computers = ObjectMapper.decode(Computer.class, list, serializer);
            List<PublicBean> publicBeans = ObjectMapper.decode(PublicBean.class, list, serializer);
            System.out.println(computers.get(1).cpu.name);
            System.out.println(publicBeans.get(2).cpu.get(PublicBean.class).name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String jsonArray = "[\n" +
            "    {\n" +
            "        \"cpu\": {\n" +
            "            \"name\": \"Intel Core i9-9900K\",\n" +
            "            \"codeName\": \"kaby Lake\",\n" +
            "            \"package\": \"Socket 1151 LGA\",\n" +
            "            \"technology\": \"14nm\",\n" +
            "            \"coreVID\": \"0.944V\",\n" +
            "            \"coreSpeed\": \"5000.00 MHz\",\n" +
            "            \"multiplier\": \"x 50.0(8 - 50)\",\n" +
            "            \"busSpeed\": \"100.00 MHz\",\n" +
            "            \"ratedFSB\": \"\",\n" +
            "            \"cores\": 8,\n" +
            "            \"threads\": 16,\n" +
            "            \"price\": 3949\n" +
            "        },\n" +
            "        \"memory\": {\n" +
            "            \"name\": \"金士顿(Kingston)\",\n" +
            "            \"type\": \"288-Pin DDR4 UDIMM\",\n" +
            "            \"size\": \"64GB\",\n" +
            "            \"dramFrequency\": \"DDR4 3200\",\n" +
            "            \"price\": 2499\n" +
            "        },\n" +
            "        \"graphics\": {\n" +
            "            \"name\": \"蓝宝石 SAPPHIRE RX 5700 XT\",\n" +
            "            \"gpu\": \"AMD\",\n" +
            "            \"technology\": \"7nm\",\n" +
            "            \"price\": 3199\n" +
            "        },\n" +
            "        \"mainboard\": {\n" +
            "            \"name\": \"技嘉 GIGABYTE\",\n" +
            "            \"type\": \"Intel平台\",\n" +
            "            \"model\": \"Z390 GAMING X\",\n" +
            "            \"price\": 1298\n" +
            "        },\n" +
            "        \"radiator\": {\n" +
            "            \"name\": \"猫头鹰\",\n" +
            "            \"coolingMode\": \"风冷\",\n" +
            "            \"price\": 699\n" +
            "        },\n" +
            "        \"hardDisk\": {\n" +
            "            \"name\": \"三星（SAMSUNG）\",\n" +
            "            \"series\": \"970 EVO 系列\",\n" +
            "            \"model\": \"MZ-V7E500BW\",\n" +
            "            \"size\": \"1T\",\n" +
            "            \"price\": 1399\n" +
            "        },\n" +
            "        \"networkAdapter\": {\n" +
            "            \"name\": \"奋威（fenvi）FV-T919\",\n" +
            "            \"type\": \"双频网卡\",\n" +
            "            \"model\": \"FV-HB1200\",\n" +
            "            \"interface\": \"PCI-E接口\",\n" +
            "            \"price\": 238\n" +
            "        },\n" +
            "        \"monitor\": {\n" +
            "            \"name\": \"戴尔 DELL\",\n" +
            "            \"model\": \"U2720QM\",\n" +
            "            \"resolution\": \"3850x2160\",\n" +
            "            \"price\": 4199\n" +
            "        },\n" +
            "        \"keyboard\": {\n" +
            "            \"name\": \"Apple\",\n" +
            "            \"type\": \"无线键盘\",\n" +
            "            \"price\": 699\n" +
            "        },\n" +
            "        \"mouse\": {\n" +
            "            \"name\": \"Apple\",\n" +
            "            \"type\": \"无线键盘\",\n" +
            "            \"price\": 539\n" +
            "        },\n" +
            "        \"systems\": [\n" +
            "            {\n" +
            "                \"name\": \"macOS\",\n" +
            "                \"version\": \"10.15.4\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"windows8\",\n" +
            "                \"version\": \"8\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"windows10\",\n" +
            "                \"version\": \"10\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"CentOS\",\n" +
            "                \"version\": \"6.5\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"Ubuntu\",\n" +
            "                \"version\": \"4.8.2\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"顶级硬件配置\",\n" +
            "        \"id\": \"EB17AA00-51C4-55BC-B863-8040ADA8DJ46\",\n" +
            "        \"desciption\": \"CPU:i9-9900K, 内存条:金士顿64GB, 显卡:蓝宝石RX5700XT\\n主板:技嘉Z390, 硬盘:三星970EVO 1T, 散热器:猫头鹰\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cpu\": {\n" +
            "            \"name\": \"Intel Core i7-9700K\",\n" +
            "            \"codeName\": \"kaby Lake\",\n" +
            "            \"package\": \"Socket 1151 LGA\",\n" +
            "            \"technology\": \"14nm\",\n" +
            "            \"coreVID\": \"0.944V\",\n" +
            "            \"coreSpeed\": \"5000.00 MHz\",\n" +
            "            \"multiplier\": \"x 50.0(8 - 50)\",\n" +
            "            \"busSpeed\": \"100.00 MHz\",\n" +
            "            \"ratedFSB\": \"\",\n" +
            "            \"cores\": 8,\n" +
            "            \"threads\": 16,\n" +
            "            \"price\": 2899\n" +
            "        },\n" +
            "        \"memory\": {\n" +
            "            \"name\": \"金士顿(Kingston)\",\n" +
            "            \"type\": \"288-Pin DDR4 UDIMM\",\n" +
            "            \"size\": \"32GB\",\n" +
            "            \"dramFrequency\": \"DDR4 3200\",\n" +
            "            \"price\": 1169\n" +
            "        },\n" +
            "        \"graphics\": {\n" +
            "            \"name\": \"蓝宝石 SAPPHIRE RX 5700\",\n" +
            "            \"gpu\": \"AMD\",\n" +
            "            \"technology\": \"7nm\",\n" +
            "            \"price\": 2599\n" +
            "        },\n" +
            "        \"mainboard\": {\n" +
            "            \"name\": \"技嘉 GIGABYTE\",\n" +
            "            \"type\": \"Intel平台\",\n" +
            "            \"model\": \"Z390 GAMING X\",\n" +
            "            \"price\": 1298\n" +
            "        },\n" +
            "        \"radiator\": {\n" +
            "            \"name\": \"猫头鹰\",\n" +
            "            \"coolingMode\": \"风冷\",\n" +
            "            \"price\": 699\n" +
            "        },\n" +
            "        \"hardDisk\": {\n" +
            "            \"name\": \"三星（SAMSUNG）\",\n" +
            "            \"series\": \"970 EVO 系列\",\n" +
            "            \"model\": \"MZ-V7E500BW\",\n" +
            "            \"size\": \"500GB\",\n" +
            "            \"price\": 799\n" +
            "        },\n" +
            "        \"networkAdapter\": {\n" +
            "            \"name\": \"奋威（fenvi）FV-T919\",\n" +
            "            \"type\": \"双频网卡\",\n" +
            "            \"model\": \"FV-HB1200\",\n" +
            "            \"interface\": \"PCI-E接口\",\n" +
            "            \"price\": 238\n" +
            "        },\n" +
            "        \"monitor\": {\n" +
            "            \"name\": \"戴尔 DELL\",\n" +
            "            \"model\": \"U2720QM\",\n" +
            "            \"resolution\": \"3850x2160\",\n" +
            "            \"price\": 4199\n" +
            "        },\n" +
            "        \"keyboard\": {\n" +
            "            \"name\": \"Apple\",\n" +
            "            \"type\": \"无线键盘\",\n" +
            "            \"price\": 699\n" +
            "        },\n" +
            "        \"mouse\": {\n" +
            "            \"name\": \"Apple\",\n" +
            "            \"type\": \"无线键盘\",\n" +
            "            \"price\": 539\n" +
            "        },\n" +
            "        \"systems\": [\n" +
            "            {\n" +
            "                \"name\": \"macOS\",\n" +
            "                \"version\": \"10.15.4\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"windows8\",\n" +
            "                \"version\": \"8\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"windows10\",\n" +
            "                \"version\": \"10\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"CentOS\",\n" +
            "                \"version\": \"6.5\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"Ubuntu\",\n" +
            "                \"version\": \"4.8.2\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"高级硬件配置\",\n" +
            "        \"id\": \"EB17AA00-51C4-55BC-B863-8040ADA8DJ46\",\n" +
            "        \"desciption\": \"CPU:i7-9700K, 内存条:金士顿32GB, 显卡:蓝宝石RX5700\\n主板:技嘉Z390, 硬盘:三星970EVO 1T, 散热器:猫头鹰\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cpu\": {\n" +
            "            \"name\": \"Intel Core i5-9600K\",\n" +
            "            \"codeName\": \"kaby Lake\",\n" +
            "            \"package\": \"Socket 1151 LGA\",\n" +
            "            \"technology\": \"14nm\",\n" +
            "            \"coreVID\": \"0.944V\",\n" +
            "            \"coreSpeed\": \"5000.00 MHz\",\n" +
            "            \"multiplier\": \"x 50.0(8 - 50)\",\n" +
            "            \"busSpeed\": \"100.00 MHz\",\n" +
            "            \"ratedFSB\": \"\",\n" +
            "            \"cores\": 8,\n" +
            "            \"threads\": 16,\n" +
            "            \"price\": 1679\n" +
            "        },\n" +
            "        \"memory\": {\n" +
            "            \"name\": \"金士顿(Kingston)\",\n" +
            "            \"type\": \"288-Pin DDR4 UDIMM\",\n" +
            "            \"size\": \"16GB\",\n" +
            "            \"dramFrequency\": \"DDR4 3200\",\n" +
            "            \"price\": 569\n" +
            "        },\n" +
            "        \"graphics\": {\n" +
            "            \"name\": \"蓝宝石 SAPPHIRE RX590\",\n" +
            "            \"gpu\": \"AMD\",\n" +
            "            \"technology\": \"7nm\",\n" +
            "            \"price\": 1249\n" +
            "        },\n" +
            "        \"mainboard\": {\n" +
            "            \"name\": \"技嘉 GIGABYTE\",\n" +
            "            \"type\": \"Intel平台\",\n" +
            "            \"model\": \"Z390 GAMING X\",\n" +
            "            \"price\": 1298\n" +
            "        },\n" +
            "        \"radiator\": {\n" +
            "            \"name\": \"猫头鹰\",\n" +
            "            \"coolingMode\": \"风冷\",\n" +
            "            \"price\": 699\n" +
            "        },\n" +
            "        \"hardDisk\": {\n" +
            "            \"name\": \"三星（SAMSUNG）\",\n" +
            "            \"series\": \"970 EVO 系列\",\n" +
            "            \"model\": \"MZ-V7E500BW\",\n" +
            "            \"size\": \"1T\",\n" +
            "            \"price\": 1399\n" +
            "        },\n" +
            "        \"networkAdapter\": {\n" +
            "            \"name\": \"奋威（fenvi）FV-T919\",\n" +
            "            \"type\": \"双频网卡\",\n" +
            "            \"model\": \"FV-HB1200\",\n" +
            "            \"interface\": \"PCI-E接口\",\n" +
            "            \"price\": 238\n" +
            "        },\n" +
            "        \"monitor\": {\n" +
            "            \"name\": \"戴尔 DELL\",\n" +
            "            \"model\": \"U2720QM\",\n" +
            "            \"resolution\": \"3850x2160\",\n" +
            "            \"price\": 4199\n" +
            "        },\n" +
            "        \"keyboard\": {\n" +
            "            \"name\": \"Apple\",\n" +
            "            \"type\": \"无线键盘\",\n" +
            "            \"price\": 699\n" +
            "        },\n" +
            "        \"mouse\": {\n" +
            "            \"name\": \"Apple\",\n" +
            "            \"type\": \"无线键盘\",\n" +
            "            \"price\": 539\n" +
            "        },\n" +
            "        \"systems\": [\n" +
            "            {\n" +
            "                \"name\": \"macOS\",\n" +
            "                \"version\": \"10.15.4\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"windows8\",\n" +
            "                \"version\": \"8\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"windows10\",\n" +
            "                \"version\": \"10\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"CentOS\",\n" +
            "                \"version\": \"6.5\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"中级硬件配置\",\n" +
            "        \"id\": \"EB17AA00-51C4-55BC-B863-8040ADA8DJ46\",\n" +
            "        \"desciption\": \"CPU:i5-9600K, 内存条:金士顿16GB, 显卡:蓝宝石RX590\\n主板:技嘉Z390, 硬盘:三星970EVO 500GB, 散热器:猫头鹰\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cpu\": {\n" +
            "            \"name\": \"Intel Core i3-9100\",\n" +
            "            \"codeName\": \"kaby Lake\",\n" +
            "            \"package\": \"Socket 1151 LGA\",\n" +
            "            \"technology\": \"14nm\",\n" +
            "            \"coreVID\": \"0.944V\",\n" +
            "            \"coreSpeed\": \"5000.00 MHz\",\n" +
            "            \"multiplier\": \"x 50.0(8 - 50)\",\n" +
            "            \"busSpeed\": \"100.00 MHz\",\n" +
            "            \"ratedFSB\": \"\",\n" +
            "            \"cores\": 8,\n" +
            "            \"threads\": 16,\n" +
            "            \"price\": 899\n" +
            "        },\n" +
            "        \"memory\": {\n" +
            "            \"name\": \"金士顿(Kingston)\",\n" +
            "            \"type\": \"288-Pin DDR4 UDIMM\",\n" +
            "            \"size\": \"8GB\",\n" +
            "            \"dramFrequency\": \"DDR4 3200\",\n" +
            "            \"price\": 289\n" +
            "        },\n" +
            "        \"graphics\": {\n" +
            "            \"name\": \"蓝宝石 SAPPHIRE RX 580\",\n" +
            "            \"gpu\": \"AMD\",\n" +
            "            \"technology\": \"7nm\",\n" +
            "            \"price\": 1069\n" +
            "        },\n" +
            "        \"mainboard\": {\n" +
            "            \"name\": \"技嘉 GIGABYTE\",\n" +
            "            \"type\": \"Intel平台\",\n" +
            "            \"model\": \"Z390 GAMING X\",\n" +
            "            \"price\": 1298\n" +
            "        },\n" +
            "        \"radiator\": {\n" +
            "            \"name\": \"猫头鹰\",\n" +
            "            \"coolingMode\": \"风冷\",\n" +
            "            \"price\": 699\n" +
            "        },\n" +
            "        \"hardDisk\": {\n" +
            "            \"name\": \"三星（SAMSUNG）\",\n" +
            "            \"series\": \"970 EVO 系列\",\n" +
            "            \"model\": \"MZ-V7E500BW\",\n" +
            "            \"size\": \"1T\",\n" +
            "            \"price\": 1399\n" +
            "        },\n" +
            "        \"networkAdapter\": {\n" +
            "            \"name\": \"奋威（fenvi）FV-T919\",\n" +
            "            \"type\": \"双频网卡\",\n" +
            "            \"model\": \"FV-HB1200\",\n" +
            "            \"interface\": \"PCI-E接口\",\n" +
            "            \"price\": 238\n" +
            "        },\n" +
            "        \"monitor\": {\n" +
            "            \"name\": \"戴尔 DELL\",\n" +
            "            \"model\": \"U2720QM\",\n" +
            "            \"resolution\": \"3850x2160\",\n" +
            "            \"price\": 4199\n" +
            "        },\n" +
            "        \"keyboard\": {\n" +
            "            \"name\": \"Apple\",\n" +
            "            \"type\": \"无线键盘\",\n" +
            "            \"price\": 699\n" +
            "        },\n" +
            "        \"mouse\": {\n" +
            "            \"name\": \"Apple\",\n" +
            "            \"type\": \"无线键盘\",\n" +
            "            \"price\": 539\n" +
            "        },\n" +
            "        \"systems\": [\n" +
            "            {\n" +
            "                \"name\": \"macOS\",\n" +
            "                \"version\": \"10.15.4\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"windows8\",\n" +
            "                \"version\": \"8\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"name\": \"windows10\",\n" +
            "                \"version\": \"10\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"name\": \"初级硬件配置\",\n" +
            "        \"id\": \"EB17AA00-51C4-55BC-B863-8040ADA8DJ46\",\n" +
            "        \"desciption\": \"CPU:i3-9100, 内存条:金士顿8GB, 显卡:蓝宝石RX580\\n主板:技嘉Z390, 硬盘:三星970EVO 250GB, 散热器:猫头鹰\"\n" +
            "    }\n" +
            "]";


    public static String json = "{\n" +
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
}
