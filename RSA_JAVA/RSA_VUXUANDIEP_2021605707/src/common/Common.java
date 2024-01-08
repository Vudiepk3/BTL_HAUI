package common;

//đọc văn bản từ một luồng đầu vào ký tự và đệm các ký tự để cung cấp cho việc đọc hiệu quả các ký tự, mảng và dòng.
import java.io.BufferedReader;
//đại diện cho một luồng đầu vào byte từ tệp
import java.io.FileInputStream;
//được sử dụng để báo cáo lỗi khi không tìm thấy tệp được yêu cầu.
import java.io.FileNotFoundException;
//được sử dụng để báo cáo lỗi khi có lỗi xảy ra trong quá trình nhập / xuất.
import java.io.IOException;
import java.io.InputStreamReader;//Lớp này đọc ký tự từ một luồng đầu vào byte và chuyển đổi chúng sang các ký tự Unicode.
// thư viện thuật toán MD5
import java.security.MessageDigest;
// được sử dụng để báo cáo lỗi khi thuật toán bảo mật được yêu cầu không khả dụng trong môi trường hiện tại
import java.security.NoSuchAlgorithmException;
//cung cấp các phương thức chuyển đổi giữa các chuỗi và các kiểu dữ liệu nguyên thuỷ như int và byte.
import javax.xml.bind.DatatypeConverter;
public class Common {
    //Phương thức này đọc nội dung của một tệp văn bản và trả về nội dung dưới dạng chuỗi.
    public static String readFile(String path) throws FileNotFoundException, IOException {
        String content = "", sNewLine;
        try ( FileInputStream fin = new FileInputStream(path)) {
            InputStreamReader isr = new InputStreamReader(fin, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((sNewLine = br.readLine()) != null) {
                content += sNewLine + "\n";
            }
            fin.close();
        }
        return content;
    }
    //Phương thức này tính toán giá trị băm MD5 của một chuỗi và trả về giá trị băm dưới dạng chuỗi hexa.
    public static String getMD5(String path) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(path.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String args[]) throws NoSuchAlgorithmException {
    }
}
