/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Brand;

import model.Category;
import model.Product;


/**
 *
 * @author Admin
 */
public class PhonesDAO extends DBContext {

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();

        String sql = "select * from Product";

        try {
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getInt("cid"),
                        rs.getInt("bid"),
                        rs.getInt("amount")
                );
                list.add(product);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
    public List<Category> getAllCategory() {
    List<Category> list = new ArrayList<>();

    String sql = "SELECT * FROM Category";

    try (PreparedStatement st = connection.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        while (rs.next()) {
            Category category = new Category(rs.getInt(1), rs.getString(2));
            list.add(category);
        }
    } catch (SQLException e) {
        
    }

    return list;
}
    
    public Category getCategoryByID(int cid) {
    String sql = "SELECT * FROM Category WHERE cid = ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, cid);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Category(
                rs.getInt("cid"),
                rs.getString("cname")
            );
        }
    } catch (SQLException e) {
        // Xử lý lỗi
    }
    return null;
}
    
    public void deleteCategory(String cid) {
    String sql = "DELETE FROM Category WHERE cid = ?";
    
    try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);

            st.executeUpdate();
    }catch(SQLException e){
        
    }
}
    
    public void deleteBrand(String bid) {
    String sql = "DELETE FROM Brand WHERE bid = ?";
    
    try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, bid);

            st.executeUpdate();
    }catch(SQLException e){
        
    }
}
    
   public void insertCategory(int cid, String cname) {
    String sql = "INSERT INTO [dbo].[Category] ([cid], [cname]) VALUES (?, ?)";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, cid);
        st.setString(2, cname);
        st.executeUpdate();
    } catch (SQLException e) {
        // Xử lý lỗi
    }
}

   public void editCategory(int cid, String newName) {
    String sql = "UPDATE [dbo].[Category] SET [cname] = ? WHERE [cid] = ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, newName);
        st.setInt(2, cid);

        st.executeUpdate();
    } catch (SQLException e) {
        // Xử lý lỗi
    }
}
   
   public void editBrand(int bid, String newName) {
    String sql = "UPDATE [dbo].[Brand] SET [bname] = ? WHERE [bid] = ?";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, newName);
        st.setInt(2, bid);

        st.executeUpdate();
    } catch (SQLException e) {
        // Xử lý lỗi
    }
}
   
   
   
   public Brand getBrandByID(int bid) {
    String sql = "SELECT * FROM Brand WHERE bid = ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, bid);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Brand(
                rs.getInt("bid"),
                rs.getString("bname")
            );
        }
    } catch (SQLException e) {
        // Xử lý lỗi
    }
    return null;
}
   
   public void insertBrand(int bid, String bname) {
    String sql = "INSERT INTO [dbo].[Brand] ([bid], [bname]) VALUES (?, ?)";

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, bid);
        st.setString(2, bname);
        st.executeUpdate();
    } catch (SQLException e) {
        // Xử lý lỗi
    }
}
   
    public List<Brand> getAllBrand() {
    List<Brand> list = new ArrayList<>();

    String sql = "SELECT * FROM Brand";

    try (PreparedStatement st = connection.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        while (rs.next()) {
            Brand brand = new Brand(rs.getInt(1), rs.getString(2));
            list.add(brand);
        }
    } catch (SQLException e) {
        
    }

    return list;
}
    
    
    public Product getLast() {
        String sql = "select top 1 * from Product order by id desc";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                rs.getInt(8));
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
public List<Product> searchPriceOrder(String sortOrder) {
    List<Product> list = new ArrayList<>();

    String sql = "SELECT * FROM Product ORDER BY price " + sortOrder;

    try {
        PreparedStatement st = connection.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("description"),
                    rs.getString("image"),
                    rs.getInt("cid"),
                    rs.getInt("bid"),
                    rs.getInt("amount")
            );
            list.add(product);
        }

    } catch (SQLException e) {
        System.out.println(e);
    }

    return list;
}
     
    public List<Product> getNewProduct() {
        List<Product> list = new ArrayList<>();

        String sql = "select top 3 * from Product order by id desc";

        try {
            PreparedStatement st=connection.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getInt("cid"),
                        rs.getInt("bid"),
                        rs.getInt("amount")
                );
                list.add(product);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
    
    public static void main(String[] args) {
    PhonesDAO dao = new PhonesDAO();
    List<Product> tourList = dao.getAll();

    for (Product tour : tourList) {
        System.out.println(tour);
    }

}
}
