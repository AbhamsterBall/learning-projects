package tries.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class GetXml {

    public static void main(String[] args) {
        try {
            // 指定要解析的 XML 文件路径
            File inputFile = new File("src\\main\\java\\tries\\xml\\test.xml");

            // 创建一个 DocumentBuilderFactory 实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            // 创建一个 DocumentBuilder 实例，用于构建 Document 对象
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // 使用 DocumentBuilder 解析 XML 文件，得到一个表示整个 XML 文档的 Document 对象
            Document doc = dBuilder.parse(inputFile);

            // 对文档进行标准化，使其易于处理
            doc.getDocumentElement().normalize();

            // 打印根元素的名称
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            // 获取文档中所有名为 "book" 的元素
            NodeList bookList = doc.getElementsByTagName("book");

            // 循环遍历所有 "book" 元素
            for (int temp = 0; temp < bookList.getLength(); temp++) {
                // 获取当前遍历到的 "book" 元素
                Node bookNode = bookList.item(temp);

                // 确保当前节点是元素节点
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    // 将当前节点转换为 Element 类型
                    Element bookElement = (Element) bookNode;

                    // 输出当前书籍的 ID
                    System.out.println("\nBook ID: " + bookElement.getAttribute("id"));

                    // 输出当前书籍的标题
                    System.out.println("Title: " + bookElement.getElementsByTagName("title").item(0).getTextContent());

                    // 输出当前书籍的作者
                    System.out.println("Author: " + bookElement.getElementsByTagName("author").item(0).getTextContent());

                    // 输出当前书籍的流派
                    System.out.println("Genre: " + bookElement.getElementsByTagName("genre").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            // 捕获可能出现的异常并打印异常信息
            e.printStackTrace();
        }

    }

}
