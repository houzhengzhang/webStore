package com.imudges.store.web.servlet;

import com.imudges.store.domain.Category;
import com.imudges.store.domain.PageModel;
import com.imudges.store.domain.Product;
import com.imudges.store.service.CategoryService;
import com.imudges.store.service.ProductService;
import com.imudges.store.service.serviceImp.CategoryServiceImp;
import com.imudges.store.service.serviceImp.ProductServiceImp;
import com.imudges.store.utils.UUIDUtils;
import com.imudges.store.utils.UploadUtils;
import com.imudges.store.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Date: 2018/11/24 17:23
 * @ Description:
 */
@WebServlet(name = "AdminProductServlet", urlPatterns = "/AdminProductServlet")
public class AdminProductServlet extends BaseServlet {
    private static final long serialVersionUID = -1440831500199938856L;

    public String findAllProductsWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前页
        int curNum = Integer.parseInt(request.getParameter("num"));
        // 调用业务层查询全部商品信息返回pagemodel
        ProductService productService = new ProductServiceImp();
        try {
            PageModel pageModel = productService.findAllProductsWithPage(curNum);
            // 将 pagemodel 放入 request
            request.setAttribute("page", pageModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 转发
        return "/admin/product/list.jsp";
    }

    public String addProductUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取全部分类信息
        CategoryService categoryService = new CategoryServiceImp();
        try {
            List<Category> categoryList = categoryService.getAllCats();
            request.setAttribute("allCats", categoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 转发
        return "/admin/product/add.jsp";
    }

    public String addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 存储表单里的数据
        Map<String, String> map = new HashMap<>();
        // 携带表单里的数据
        Product product = new Product();
        try {
            // 由于上传文件 通过request.getInputStream() 对请求体内数据进行拆分和封装
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> fileItemList = fileUpload.parseRequest(request);

            // 遍历集合
            for (FileItem item : fileItemList) {
                // 普通项
                if (item.isFormField()) {
                    map.put(item.getFieldName(), item.getString("utf-8"));
                } else {
                    // 上传项
                    // 获取要原始文件名
                    String oldFileName = item.getName();
                    // 生成服务器上的文件名
                    String newFileName = UploadUtils.getUUIDName(oldFileName);
                    InputStream is = item.getInputStream();
                    // 获取绝对路径
                    String realPath = getClass().getClassLoader().getResource("/").getPath();
                    int index = realPath.indexOf("WEB-INF");
                    realPath = realPath.substring(1, index);
                    String dir = UploadUtils.getDir("");
                    String path = realPath + "products/3" + dir;

                    File newDir = new File(path);
                    if (!newDir.exists()) {
                        // 递归创建文件目录
                        newDir.mkdirs();
                    }
                    // 创建一个空文件夹
                    File finalFile = new File(newDir, newFileName);
                    if (!finalFile.exists()) {
                        finalFile.createNewFile();
                    }
                    // 建立空文件对应的输出流
                    OutputStream os = new FileOutputStream(finalFile);
                    // 将输入流的数据刷到输出流中
                    IOUtils.copy(is, os);
                    // 释放资源
                    IOUtils.closeQuietly(is);
                    IOUtils.closeQuietly(os);
 
                    map.put("pimage", finalFile.getPath().substring(
                            finalFile.getPath().indexOf("products"), finalFile.getPath().length()).replace("\\", "/"));
                }
            }

            // 将 map 里的数据填充到product上
            BeanUtils.populate(product, map);
            product.setPid(UUIDUtils.getId());
            product.setPdate(new Date());
            product.setPflag(0);

            // 调用业务层 存入数据库
            ProductService productService = new ProductServiceImp();
            productService.addProduct(product);

            // 重定向
            response.sendRedirect("/store/AdminProductServlet?method=findAllProductsWithPage&num=1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
