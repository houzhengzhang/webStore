/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : store

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-12-03 12:54:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `email` varchar(30) NOT NULL,
  `password` varchar(35) DEFAULT NULL,
  `adminname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '手机数码');
INSERT INTO `category` VALUES ('172934bd636d485c98fd2d3d9cccd409', '电脑配件');
INSERT INTO `category` VALUES ('2', '电脑办公');
INSERT INTO `category` VALUES ('3', '家具家居');
INSERT INTO `category` VALUES ('4', '鞋靴箱包');
INSERT INTO `category` VALUES ('5', '图书音像');
INSERT INTO `category` VALUES ('6', '母婴孕婴');
INSERT INTO `category` VALUES ('afdba41a139b4320a74904485bdb7719', '汽车用品');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `itemid` varchar(32) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `pid` varchar(32) DEFAULT NULL,
  `oid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `order_item_fk_0001` (`pid`),
  KEY `order_item_fk_0002` (`oid`),
  CONSTRAINT `order_item_fk_0001` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `order_item_fk_0002` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` varchar(32) NOT NULL,
  `ordertime` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `order_fk_0001` (`uid`),
  CONSTRAINT `order_fk_0001` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` varchar(32) NOT NULL,
  `pname` varchar(50) DEFAULT NULL,
  `market_price` double DEFAULT NULL,
  `shop_price` double DEFAULT NULL,
  `pimage` varchar(200) DEFAULT NULL,
  `pdate` date DEFAULT NULL,
  `is_hot` int(11) DEFAULT NULL,
  `pdesc` varchar(255) DEFAULT NULL,
  `pflag` int(11) DEFAULT '0',
  `cid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `product_fk_0001` (`cid`),
  CONSTRAINT `product_fk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('168DC146921343A2986DC24FCACBE07D', '华为 HUAWEI P20 AI', '3388', '3288', 'products/3/0/0/63B99BCE717742A4808F6D7617E43AED.jpg', '2018-12-01', '0', '华为 HUAWEI P20 AI智慧全面屏 6GB+64GB 极光闪蝶色 全网通版 移动联通电信4G手机 双卡双待', '0', '1');
INSERT INTO `product` VALUES ('24B97C0BDBAC448FB725DB78B8319050', 'Apple iPhone X (A1865) 64GB', '6999', '6999', 'products/3/0/0/C8460A54E6F34F75BD517950AA554ACA.jpg', '2018-12-01', '1', 'Apple iPhone X (A1865) 64GB 银色 移动联通电信4G手机 后置摄像头：双1200万像素 前置摄像头：700万像素', '0', '1');
INSERT INTO `product` VALUES ('39ADE71A11614D1C95F5CA194F981B1A', '戴尔(DELL)灵越5680', '7198', '7198', 'products/3/0/0/5D20B8B6CA684808876F801F608412F7.jpg', '2018-12-01', '0', '戴尔(DELL)灵越5680游戏台式电脑主机(八代i7-8700 Z370主板 8G 128GSSD 1T GTX1060 6G独显 WIFI 蓝牙)', '0', '2');
INSERT INTO `product` VALUES ('4037B7E8697E427B89CA90AB7DB79478', '武极 i7 8700/华硕', '5299', '5299', 'products/3/0/0/7436A640584048EAA4C7ADD85DAEBC64.jpg', '2018-12-01', '0', '武极 i7 8700/华硕GTX1060/240G 游戏台式吃鸡电脑主机/DIY组装机', '0', '2');
INSERT INTO `product` VALUES ('45D51C58C7D546CDA3CA9BB695F5D754', '戴尔灵越14 燃 ', '5299', '5299', 'products/3/0/0/893D7389BD894364BA397C80B9D53298.jpg', '2018-12-01', '1', '戴尔灵越14 燃 14英寸轻薄窄边框笔记本电脑(i5-8265U 8G 256GSSD MX150 2G独显 背光键盘）冰河银', '0', '2');
INSERT INTO `product` VALUES ('4D9461984EDD4C56BEE18EC0E03BCE16', '华为 HUAWEI Mate 20 ', '4499', '4399', 'products/3/0/0/3642625B8572490EA7A09D014091580E.jpg', '2018-12-01', '1', '华为 HUAWEI Mate 20 麒麟980AI智能芯片全面屏超微距影像超大广角徕卡三摄6GB+128GB翡冷翠全网通版双4G手机', '0', '1');
INSERT INTO `product` VALUES ('66AF3F59466944BC80EA53D259427242', '机械革命(MECHREVO)', '8299', '8199', 'products/3/0/0/01152076E1C9491D85D8686AB16E0AE6.jpg', '2018-12-01', '1', '机械革命(MECHREVO) NX6游戏台式电脑主机（i7-8700 16G 128GSSD+1T GTX1060*6G独显 三年上门）', '0', '2');
INSERT INTO `product` VALUES ('78E7689D5A9348F2BD01545A9AD52B41', '宏硕 酷睿i5-8400/I7四核', '3599', '3599', 'products/3/0/0/056A36BC632944B7A2CCFE59B5A4C89A.jpg', '2018-12-01', '0', '宏硕 酷睿i5-8400/I7四核 /送显示器/GTX1060独显/吃鸡游戏台式机DIY组装电脑主机 电脑整机 酷睿i7/16G/GTX1060独显', '0', '2');
INSERT INTO `product` VALUES ('91D789B1884D46C494E8CA5E3C681E3F', 'AOC AG320FCH8/3R 31.5英寸曲面 ', '1399', '1299', 'products/3/0/0/D6178C79598A4FE9958E71C6DF115F7C.jpg', '2018-12-01', '0', 'AOC AG320FCH8/3R 31.5英寸曲面 1800R曲率吃鸡游戏电竞电脑显示器', '0', '2');
INSERT INTO `product` VALUES ('9A550F511FA44E00885FACDA827534CF', '联想（Lenovo）拯救者 刃7000Ⅱ', '7198', '7098', 'products/3/0/0/B61BDCF787E64FB584B6A88BD25285C4.jpg', '2018-12-01', '1', '联想（Lenovo）拯救者 刃7000Ⅱ UIY吃鸡游戏台式电脑主机(六核I7-8700 8G 512G GTX1060 6G 三年上门)', '0', '2');
INSERT INTO `product` VALUES ('A2644C92FF984FFE967DE8DAA2488DF0', '英伟达（NVIDIA） GeForce RTX 2080 Ti ', '9999', '9999', 'products/3/0/0/20D0FBF2EFBD45C792BD322015D764E5.jpg', '2018-12-01', '1', 'ROG-STRIX-GeForce RTX2080 TI-O11G显卡的核心为TU102,CUDA数量为4352，加速频率为1665MHz，内存为11GB，352BIT显存位宽，14GBPS的显存频率，电源接口为8+8pin', '0', '172934bd636d485c98fd2d3d9cccd409');
INSERT INTO `product` VALUES ('AAC639AA069D4BD7B0A63BF7C76E6814', 'Surface Pro 6 二合一平板电脑笔记本', '6988', '6888', 'products/3/0/0/67E52863130D401AB9D508B03E1EC993.jpg', '2018-12-01', '1', '【亮铂金键盘套装】微软（Microsoft）Surface Pro 6 二合一平板电脑笔记本 12.3英寸（第八代 i5 8G 128G ）', '0', '2');
INSERT INTO `product` VALUES ('B9B0513522214199A6D5CA084D5A062D', '联想(Lenovo)拯救者Y7000P ', '7799', '7799', 'products/3/0/0/05F38BB7CBA44F2280D016760B3E5FCA.jpg', '2018-12-01', '0', '联想(Lenovo)拯救者Y7000P 英特尔八代酷睿15.6英寸游戏笔记本电脑(i5-8300H 8G 512G PCIE GTX1060 144Hz黑)', '0', '2');
INSERT INTO `product` VALUES ('CD6E8F1C7DF04434A9F1B376EE76CB99', '机械革命(MECHREVO)S1 ', '5398', '5398', 'products/3/0/0/AEBAC244F725423097F5079955E14A0D.jpg', '2018-12-01', '0', '机械革命(MECHREVO)S1 72%IPS1.18kg14英寸独显窄边框轻薄笔记本电脑i7-8550U 8G 256G MX150 灰 OFFICE 背光', '0', '2');
INSERT INTO `product` VALUES ('CEFA75F8C19D4A1FB760B0FB49885DBD', '惠普（HP）暗影精灵4代 ', '6999', '6899', 'products/3/0/0/E12025611B664159B80E5B6D6216B664.jpg', '2018-12-01', '1', '惠普（HP）暗影精灵4代 15.6英寸游戏笔记本电脑（i5-8300H 8G 128G+1TB GTX1060 6G独显 四区背光键盘 IPS）', '0', '2');
INSERT INTO `product` VALUES ('CFB923E54EBC4740B1D8688FF8B400FE', '华硕(ASUS) 飞行堡垒6 15.6英寸', '6999', '6899', 'products/3/0/0/A8EAE9C630DA42D097A214082EE0B442.jpg', '2018-12-01', '1', '华硕(ASUS) 飞行堡垒6 15.6英寸窄边框游戏笔记本电脑(i7-8750H 8G 256GSSD+1T GTX1050Ti 4G IPS)火陨红黑', '0', '2');
INSERT INTO `product` VALUES ('DD9AC9AFB863485E8FF6AF2D9D4EF154', '戴尔(DELL)成就3670商用办公台式机', '5799', '5799', 'products/3/0/0/2D29C355DACE4C0B9FFF0B633FD10640.jpg', '2018-12-01', '0', '戴尔(DELL)成就3670商用办公台式电脑整机(八代i7-8700 8G 1T 2G 三年上门 键鼠 WIFI 蓝牙)21.5英寸', '0', '2');
INSERT INTO `product` VALUES ('E7DD79381DAF4E1391F12423E2B67B45', '联想ThinkPad 翼480', '5299', '5199', 'products/3/0/0/8FBB2121CA63457B8703E5D7A1636FC7.jpg', '2018-12-02', '1', '联想ThinkPad 翼480 英特尔8代酷睿14英寸轻薄窄边框笔记本电脑（i5-8250U 8G 256GSSD 2G独显 FHD IPS屏）银', '0', '2');
INSERT INTO `product` VALUES ('EB452487EF434E7ABB56815A5064330C', '小米(MI)Air 13.3英寸全金属超轻薄笔记本电脑', '5799', '5699', 'products/3/0/0/CEA59267DC38435394A346F1953BB73A.jpg', '2018-12-01', '1', '小米(MI)Air 13.3英寸全金属超轻薄笔记本电脑(i7-8550U 8G 256G PCIE SSD MX150 2G独显 72%NTSC FHD 预装Office 指纹版)银', '0', '2');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(35) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  `code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
