/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Item;
import model.Order;
import model.OrderLine;
import model.Product;



public class DAO extends DBContext{
    public Account checkAccount(String username, String password) {
        String sql = " select * from [Account]\n"
                + " where [Username] = ?\n"
                + " and Password = ?";

        try {
            PreparedStatement st;
            st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt("accid"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role"),
                rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Account checkAccountExist(String username){
        String sql="SELECT * FROM Account WHERE Username=?";
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt("accid"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("role"),
                rs.getString("email"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public void signup(String username,String password){
        String sql="insert into Account\n"
                + "values(?,?,2)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.executeUpdate();
    }catch(SQLException e){
        
    }
    
    }
    
    public void change(Account a){
        String sql="update Account set password=? where username=?";
        try{
            PreparedStatement st=connection.prepareStatement(sql);
            st.setString(1, a.getPassword());
            st.setString(2, a.getUsername());
            st.executeUpdate();
        }catch(SQLException e){
            
        }
        
    }
    
   

public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product\n"
                + "where cid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
         ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),

                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                rs.getInt(8)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
    List<Product> list = new ArrayList<>();
    String sql = "SELECT * FROM Product WHERE [name] LIKE ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, "%" + txtSearch + "%");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                rs.getInt(1),
                rs.getString(2),
                rs.getDouble(3),
                rs.getString(4),
                rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getInt(8)
            ));
        }
    } catch (SQLException e) {
       
    }
    return list;
    }
    
    
    public void deleteProduct(String pid) {
    String sql = "DELETE FROM Product WHERE id = ?";
    
    try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pid);

            st.executeUpdate();
    }catch(SQLException e){
        
    }
}
    
    public void insertProduct(String name, String price, String description, String image, int cid, int bid,int amount) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([name]\n"
                + "           ,[price]\n"
                + "           ,[description]\n"
                + "           ,[image]\n"
                + "           ,[cid]\n"
                + "           ,[bid]\n"
                + ",[amount])\n"
                + "     VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, price);
            st.setString(3, description);
            st.setString(4, image);
            st.setInt(5, cid);
            st.setInt(6, bid);
             st.setInt(7, amount);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
    
    public List<Product> getProductRelated(int cid, int pid) {
        List<Product> list = new ArrayList<>();
        String sql = "select top(3) * from product\n"
                + "where cid = ? and id not in (?)\n"
                + "order by newid()";
        try {
            PreparedStatement st;
            st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.setInt(2, pid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),

                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                rs.getInt(8)
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Product getProductByID(int id) {
        String sql = "select * from product\n"
                + "where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
         ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Product(
                rs.getInt(1),
                rs.getString(2),
                rs.getDouble(3),
                rs.getString(4),
                rs.getString(5),
                    rs.getInt(6),
                   rs.getInt(7),
                        rs.getInt(8)
            );
            }
        } catch (SQLException e) {
        }
        return null;
    }
 
    public void editProduct(String name, String price, String description, String image, int cid, int bid,int amount,String id) {
         String sql = "UPDATE [dbo].[Product] " +
                 "SET [name] = ?, " +
                 "    [price] = ?, " +
                 "    [description] = ?, " +
                 "    [image] = ?, " +
                 "    [cid] = ?, " +
                 "    [bid] = ?, " +
                 "    [amount] = ? " +
                 "WHERE [id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, price);
            st.setString(3, description);
            st.setString(4, image);
            st.setInt(5, cid);
            st.setInt(6, bid);
             st.setInt(7, amount);
              st.setString(8, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
   
   
   public int getTotalProduct(){
       String sql="select count(*) from Product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            
        }
       return 0;
   }
   
    public List<Product> pageProduct(int index) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product\n"
                + "order by id\n"
                + "offset ? rows fetch next 6 rows only";
         try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,(index-1)*6);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                list.add(new Product(
                rs.getInt(1),
                rs.getString(2),
                rs.getDouble(3),
                rs.getString(4),
                rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                        rs.getInt(8)
            ));
            }
        }catch(SQLException e){
            
        }
        return list;
    }
    
    public int getTotalProductSearch(String txtSearch) {
    String sql = "select count(*) from product\n"
                + "where [name] like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public List<Product> pageSearch(String txtSearch,int index) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product WHERE [name] LIKE ?\n"
                + "order by id\n"
                + "offset ? rows fetch next 6 rows only";
         try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            st.setInt(2,(index-1)*6);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                list.add(new Product(
                rs.getInt(1),
                rs.getString(2),
                rs.getDouble(3),
                rs.getString(4),
                rs.getString(5),
                    rs.getInt(6),
                    rs.getInt(7),
                        rs.getInt(8)
            ));
            }
        }catch(SQLException e){
            
        }
        return list;
    }
            
    
    
           public double getTotalMoney(List<Item> cart) {
        double total = 0.0;
        for (int i = 0; i < cart.size(); i++) {
            total += cart.get(i).getProduct().getPrice() * cart.get(i).getQuantity();
        }
        return total;
    }

    public Item getItemByProduct(Product p, List<Item> cart) {
        Item itemToGet = null;
        for (Item item : cart) {
            if (item.getProduct().getId() == p.getId()) {
                itemToGet = item;
                break;
            }
        }
        return itemToGet;
    }
   
     public void addOrder(Account a, List<Item> cart) {
        DAO d = new DAO();
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            //add a order
            String sql1 = "INSERT INTO [dbo].[Order]([date],[userID],[TotalMoney])\n"
                    + "     VALUES(?,?,?)";
            PreparedStatement st1;
            st1 = connection.prepareStatement(sql1);
            st1.setString(1, date);
            st1.setInt(2, a.getId());
            st1.setDouble(3, d.getTotalMoney(cart));
            st1.executeUpdate();
            //lấy id của order vừa add
            String sql2 = "select top 1 cart_id from [Order]\n"
                    + "order by cart_id desc\n";
            PreparedStatement st2;
            st2 = connection.prepareStatement(sql2);
            ResultSet rs = st2.executeQuery();
            //add vào bảng orderLine
            if (rs.next()) {
                int oId = rs.getInt("cart_id");
                for (Item item : cart) {
                    String sql3 = "INSERT INTO [dbo].[Orderline]([cart_id],[product_id],[quantity])\n"
                            + "     VALUES(?,?,?)";
                    PreparedStatement st3;
                    st3 = connection.prepareStatement(sql3);
                    st3.setInt(1, oId);
                    st3.setInt(2, item.getProduct().getId());
                    st3.setInt(3, item.getQuantity());
                    st3.executeUpdate();
                }
            }
            //cập nhật lại số lượng sản phẩm 
            String sql4 = "UPDATE [dbo].[Product]\n"
                    + "   SET amount = amount - ?\n"
                    + " WHERE id = ?";
            PreparedStatement st4;
            st4 = connection.prepareStatement(sql4);
            for (Item item : cart) {
                st4.setInt(1, item.getProduct().getAmount());
                st4.setInt(2, item.getProduct().getId());
                st4.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Order getOrder() {
    String sql = "SELECT TOP 1 * FROM [Order] ORDER BY cart_id DESC";
    try (
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        if (resultSet.next()) {
            return new Order(
                resultSet.getInt("cart_id"),
                resultSet.getInt("userID"),
                resultSet.getDouble("TotalMoney"),
                resultSet.getDate("date")
            );
        }
    } catch (SQLException e) {
       
    }
    return null;
}

public List<OrderLine> getAllOrderDetail(int orderId) {
    List<OrderLine> list = new ArrayList<>();
    String sql = "SELECT * FROM [Orderline] WHERE cart_id = ?";
    try (
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, orderId);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                OrderLine orderLine = new OrderLine(
                    resultSet.getInt("cart_item_id"),
                    resultSet.getInt("cart_id"),
                    resultSet.getInt("product_id"),
                    resultSet.getInt("quantity")
                );
                list.add(orderLine);
            }
        }
    } catch (SQLException e) {
        
    }
    return list;
}

public List<Product> getListProductByOrder(List<OrderLine> listOrder) {
    List<Product> listProducts = new ArrayList<>();
    for (OrderLine orderDetail : listOrder) {
        Product product = getProductByID(orderDetail.getPid());
        if (product != null) {
            listProducts.add(product);
        }
    }
    return listProducts;
}
     
public double totalMoneyMonth(int month) {
        String sql = "SELECT SUM(TotalMoney) \n" +
"FROM [Order] WHERE MONTH(date) = ?\n" +
"GROUP BY MONTH(date)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

public double totalMoney(int month) {
    String sql = "SELECT SUM(TotalMoney) FROM [Order] WHERE MONTH(date) = ?";
    
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, month);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble(1);
        }
    } catch (SQLException e) {
    }
    return 0;
}

public double[] totalMoney12Months() {
    double[] totalMoneyByMonth = new double[12];

    for (int i = 1; i <= 12; i++) {
        totalMoneyByMonth[i - 1] = totalMoneyMonth(i);
    }

    return totalMoneyByMonth;
}

public int quantityMonth(int month) {
    String sql = "SELECT MONTH([Order].date) AS Month, SUM(Orderline.Quantity) AS TotalQuantity\n" +
            "FROM Orderline\n" +
            "JOIN [Order] ON Orderline.cart_id = [Order].cart_id\n" +
            "WHERE MONTH([Order].date) = ?\n" +
            "GROUP BY MONTH([Order].date)";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, month);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("TotalQuantity");
        }
    } catch (SQLException e) {
       
    }
    return 0;
}


public Map<String, Object> productHot() {
    String sql = "SELECT TOP (1) [product_id], COUNT([product_id]) AS damua\n" +
        "FROM [PRJ301].[dbo].[Orderline]\n" +
        "GROUP BY [product_id]\n" +
        "ORDER BY damua DESC";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int productId = rs.getInt("product_id");
            int damua = rs.getInt("damua");
            
            String productInfoSql = "SELECT [name] FROM [Product] WHERE [id] = ?";
            PreparedStatement productInfoPS = connection.prepareStatement(productInfoSql);
            productInfoPS.setInt(1, productId);
            ResultSet productInfoRS = productInfoPS.executeQuery();
            String productName = productInfoRS.next() ? productInfoRS.getString("name") : "Unknown Product";

            Map<String, Object> result = new HashMap<>();
            result.put("product_id", productId);
            result.put("damua", damua);
            result.put("productName", productName);
            
            return result;
        }
    } catch (SQLException e) {
       
    }
    return Collections.emptyMap();
}

public List<Map<String, Object>> getHotProductsLast7Days() {
    String sql = "SELECT [product_id], COUNT([product_id]) AS totalPurchases\n" +
                 "FROM [PRJ301].[dbo].[Orderline] o\n" +
                 "INNER JOIN [PRJ301].[dbo].[Order] ord ON o.[cart_id] = ord.[cart_id]\n" +
                 "WHERE ord.[date] >= DATEADD(DAY, -7, GETDATE())\n" +
                 "GROUP BY [product_id]\n" +
                 "ORDER BY totalPurchases DESC";

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        List<Map<String, Object>> productList = new ArrayList<>();
        while (rs.next()) {
            int productId = rs.getInt("product_id");
            int totalPurchases = rs.getInt("totalPurchases");

            String productName = getProductNameForProductId(productId);

            Map<String, Object> product = new HashMap<>();
            product.put("product_id", productId);
            product.put("productName", productName);
            product.put("totalPurchases", totalPurchases);

            productList.add(product);
        }
        return productList;
    } catch (SQLException e) {
        // Xử lý ngoại lệ
    }
    return Collections.emptyList();
}

// Phương thức để lấy tên sản phẩm dựa trên product_id
private String getProductNameForProductId(int productId) {
    String productName = "Unknown Product";
    String productInfoSql = "SELECT [name] FROM [Product] WHERE [id] = ?";
    try (PreparedStatement productInfoPS = connection.prepareStatement(productInfoSql)) {
        productInfoPS.setInt(1, productId);
        try (ResultSet productInfoRS = productInfoPS.executeQuery()) {
            if (productInfoRS.next()) {
                productName = productInfoRS.getString("name");
            }
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ
    }
    return productName;
}

public Map<String, Object> accHOT() {
    String sql = "SELECT TOP (1) [userID], COUNT([userID]) AS totalOrders\n" +
                 "FROM [PRJ301].[dbo].[Order]\n" +
                 "GROUP BY [userID]\n" +
                 "ORDER BY totalOrders DESC";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int userID = rs.getInt("userID");
            int totalOrders = rs.getInt("totalOrders");
            
            String username = getUsernameForUserID(userID); // Lấy tên dựa trên userID
            
            Map<String, Object> result = new HashMap<>();
            result.put("userID", userID);
            result.put("username", username);
            result.put("totalOrders", totalOrders);
            
            return result;
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ
    }
    return Collections.emptyMap();
}



public List<Map<String, Object>> topBuyers(int limit) {
    String sql = "SELECT [userID], COUNT([userID]) AS totalOrders\n" +
                 "FROM [PRJ301].[dbo].[Order]\n" +
                 "GROUP BY [userID]\n" +
                 "ORDER BY totalOrders DESC";
    
    if (limit > 0) {
        sql = "SELECT TOP " + limit + " [userID], COUNT([userID]) AS totalOrders\n" +
              "FROM [PRJ301].[dbo].[Order]\n" +
              "GROUP BY [userID]\n" +
              "ORDER BY totalOrders DESC";
    }

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        List<Map<String, Object>> resultList = new ArrayList<>();
        while (rs.next()) {
            int userID = rs.getInt("userID");
            int totalOrders = rs.getInt("totalOrders");
            
            String username = getUsernameForUserID(userID);

            Map<String, Object> result = new HashMap<>();
            result.put("userID", userID);
            result.put("username", username);
            result.put("totalOrders", totalOrders);
            
            resultList.add(result);
        }
        return resultList;
    } catch (SQLException e) {
        // Xử lý ngoại lệ
    }
    return Collections.emptyList();
}

public List<Map<String, Object>> getTopBuyersLast7Days() {
    String sql = "SELECT [userID], COUNT([userID]) AS totalOrders\n" +
                 "FROM [PRJ301].[dbo].[Order]\n" +
                 "WHERE [date] >= DATEADD(DAY, -7, GETDATE())\n" +
                 "GROUP BY [userID]\n" +
                 "ORDER BY totalOrders DESC";

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        List<Map<String, Object>> topBuyersList = new ArrayList<>();
        while (rs.next()) {
            int userID = rs.getInt("userID");
            int totalOrders = rs.getInt("totalOrders");
            String username = getUsernameForUserID(userID);

            Map<String, Object> topBuyer = new HashMap<>();
            topBuyer.put("userID", userID);
            topBuyer.put("username", username);
            topBuyer.put("totalOrders", totalOrders);

            topBuyersList.add(topBuyer);
        }
        return topBuyersList;
    } catch (SQLException e) {
        // Xử lý ngoại lệ
    }
    return Collections.emptyList();
}

// Phương thức để lấy tên dựa trên userID
private String getUsernameForUserID(int userID) {
    String username = null;
    String usernameSql = "SELECT [username] FROM [Account] WHERE [accid] = ?";
    try {
        PreparedStatement usernamePS = connection.prepareStatement(usernameSql);
        usernamePS.setInt(1, userID);
        ResultSet usernameRS = usernamePS.executeQuery();
        if (usernameRS.next()) {
            username = usernameRS.getString("username");
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ
    }
    return username;
}



public ArrayList<Product> getProductByCidPrice(String cid, String head, String end) throws SQLException {

        ArrayList<Product> list = new ArrayList<>();
        if (head == null && end == null && cid != null) {
            String sql = "SELECT * FROM Product WHERE cid=?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, cid);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product c = new Product();
                     c.setId(rs.getInt(1));
                    c.setName(rs.getString(2));
                    
                    c.setPrice(rs.getDouble(3));
                    c.setDescription(rs.getString(4));
                    c.setImage_url(rs.getString(5));
                    c.setCid(rs.getInt(6));
                    c.setBid(rs.getInt(7));
                    c.setAmount(rs.getInt(8));
                    list.add(c);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        else if (cid == null && head != null && end != null) {
            String sql = "SELECT * FROM Product WHERE price>=? and price <?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, head);
                st.setString(2, end);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product c = new Product();
                    c.setId(rs.getInt(1));
                    c.setName(rs.getString(2));
                    
                    c.setPrice(rs.getDouble(3));
                    c.setDescription(rs.getString(4));
                    c.setImage_url(rs.getString(5));
                    c.setCid(rs.getInt(6));
                    c.setBid(rs.getInt(7));
                    c.setAmount(rs.getInt(8));
                    list.add(c);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else if (cid != null && head != null && end != null) {
            String sql = "SELECT * FROM Product WHERE cid=? and price>=? and price <?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, cid);
                st.setString(2, head);
                st.setString(3, end);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product c = new Product();
                    c.setId(rs.getInt(1));
                    c.setName(rs.getString(2));
                    
                    c.setPrice(rs.getDouble(3));
                    c.setDescription(rs.getString(4));
                    c.setImage_url(rs.getString(5));
                    c.setCid(rs.getInt(6));
                    c.setBid(rs.getInt(7));
                    c.setAmount(rs.getInt(8));
                    list.add(c);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }

public List<Product> getAllProductBySort(String priceOrName, String typeSort) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from product order by ";
        if (priceOrName.equals("price")) {
            sql += "price";
        } else if (priceOrName.equals("name")) {
            sql += "name";
        } 

        if (typeSort.equals("asc")) {
            sql += " ASC";
        } else if (typeSort.equals("desc")) {
            sql += " DESC";
        }
        try {
            PreparedStatement st;
            st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                rs.getInt(8));
                
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }



//    public static void main(String[] args) {
//    DAO dao = new DAO();
//    double totalMoney = dao.productHot();
//    System.out.println("Total Money for Month 10: " + totalMoney);
//
//    }
    
    
public List<Map<String, Object>> topSellingProducts(int limit) {
    String sql = "SELECT TOP (?) [product_id], COUNT([product_id]) AS damua\n" +
        "FROM [PRJ301].[dbo].[Orderline]\n" +
        "GROUP BY [product_id]\n" +
        "ORDER BY damua DESC";

    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, limit); // Thay đổi số lượng sản phẩm bạn muốn lấy (5 trong trường hợp này)
        ResultSet rs = ps.executeQuery();

        List<Map<String, Object>> resultList = new ArrayList<>();
        while (rs.next()) {
            int productId = rs.getInt("product_id");
            int damua = rs.getInt("damua");

            String productInfoSql = "SELECT [name] FROM [Product] WHERE [id] = ?";
            PreparedStatement productInfoPS = connection.prepareStatement(productInfoSql);
            productInfoPS.setInt(1, productId);
            ResultSet productInfoRS = productInfoPS.executeQuery();
            String productName = productInfoRS.next() ? productInfoRS.getString("name") : "Unknown Product";

            Map<String, Object> result = new HashMap<>();
            result.put("product_id", productId);
            result.put("damua", damua);
            result.put("productName", productName);

            resultList.add(result);
        }
        return resultList;
    } catch (SQLException e) {
        // Xử lý lỗi
    }
    return Collections.emptyList();
}


public void getNewPass(String email, String pass) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?\n"
                + " WHERE [email] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, email);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

 public boolean insertEmail(String username, String email) {
    String sql = "UPDATE [PRJ301].[dbo].[Account] " +
                 "SET [email] = ? " +
                 "WHERE [username] = ?";

    try {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, username);

        int rowsUpdated = preparedStatement.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
 
 
 public double totalDate(int daysAgo) {
    String sql = "SELECT\n" +
                 "    [date],\n" +
                 "    SUM([TotalMoney]) AS TotalMoneyPerDay\n" +
                 "FROM [PRJ301].[dbo].[Order]\n" +
                 "WHERE [date] BETWEEN DATEADD(DAY, -?, GETDATE()) AND GETDATE()\n" +
                 "GROUP BY [date]\n" +
                 "ORDER BY [date] DESC";
    
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, daysAgo);
        ResultSet rs = ps.executeQuery();
        double totalRevenue = 0.0;
        while (rs.next()) {
            totalRevenue += rs.getDouble("TotalMoneyPerDay");
        }
        return totalRevenue;
    } catch (SQLException e) {
        // Xử lý lỗi ở đây
    }
    return 0.0;
}

public static void main(String[] args) {
    DAO dao = new DAO();
    int numberOfProducts = 3; // Số lượng sản phẩm bạn muốn lấy
    List<Map<String, Object>> topSellingProducts = dao.topSellingProducts(numberOfProducts);
    
    if (topSellingProducts != null && !topSellingProducts.isEmpty()) {
        for (Map<String, Object> product : topSellingProducts) {
            int productId = (int) product.get("product_id");
            int damua = (int) product.get("damua");
            String productName = (String) product.get("productName");
            
            System.out.println("Product ID: " + productId);
            System.out.println("Số lượng bán được: " + damua);
            System.out.println("Tên sản phẩm: " + productName);
            System.out.println("---------------");
        }
    } else {
        System.out.println("No data found.");
    }
}





// Phương thức để lấy tên dựa trên userID




}
