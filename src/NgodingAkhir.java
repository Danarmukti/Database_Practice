import java.util.Scanner;
import java.sql.*;
import java.sql.SQLException;
public class NgodingAkhir {
    /**
     * Danar Mukti Wibowo R37 202143502476
     */
    public static void main(String[] args) {
        try {
            String ulang;
            int pilih; 
            System.out.println("\t\t-TOKO BUAH-");
            System.out.println("----------------------------------------------");
            System.out.println("\t\t\t-MENU-");
            System.out.println("==============================================");
            System.out.println("1.Menampilkan Supply");
            System.out.println("2.Menambahkan Stok Supply");
            System.out.println("3.Merubah Supply");
            System.out.println("4.Menghapus Supply");
            System.out.println("5.Menambah Data Baru");
            System.out.println("6.exit");
            System.out.println("==============================================");
            do {
                
                System.out.print("Masukan Pilihan : ");
                Scanner input = new Scanner(System.in);
                Scanner ganti = new Scanner(System.in);
                pilih =  input.nextInt();
                System.out.println("----------------------------------------------");   
                
                Class.forName("org.sqlite.JDBC"); 
                String url = "jdbc:sqlite:D:/sqlite/TugasAkhir.db"; 
                Connection koneksi = DriverManager.getConnection(url);
                Statement stat = koneksi.createStatement();
                
                switch (pilih) {
                    // menampilkan isi database
                    case 1:
                    ResultSet set = stat.executeQuery("select * from supplyBuah");
                    System.out.println("Kode\tNama\t\tstok\t\tharga_PerKilo\ttgl exp");
                    System.out.print("--------------------------------------------------------------------------\n");
                    while (set.next()) {
                        String kode = set.getString("kode");
                        System.out.print(kode);
                        String nama = set.getString("Nama");
                        System.out.print("\t"+nama);
                        int stok = set.getInt("stok");
                        System.out.print("\t\t"+stok);
                        int harga = set.getInt("harga_PerKilo");
                        System.out.print("\t\t"+harga);
                        String exp = set.getString("tgl_exp");
                        System.out.print("\t\t"+exp);
                        System.out.println();
                    }
                        break;
                        case 2:
                        System.out.println("Menambahkan stok");
                        System.out.print("Masukan kode : "); String kode = ganti.nextLine();
                        System.out.print("Masukan Jumlah Stok : "); int ubah = input.nextInt();
                        stat.executeUpdate("update supplyBuah set stok=(stok+"+ubah+") where kode='"+kode+"';");
                        break;

                        case 3:
                        int harga;
                        String buah;
                        String exp;
                        System.out.println("Merubah Supply");
                        System.out.print("Masukan kode yang ingin dirubah : "); kode = ganti.nextLine();  
                        System.out.print("Masukan kode Baru               : "); String ubahkode = ganti.nextLine();  
                        System.out.print("Nama buah yang baru             : "); buah = ganti.nextLine();
                        System.out.print("Tanggal Exp yang baru           : "); exp = ganti.nextLine();
                        System.out.print("Harga yang baru                 : "); harga = ganti.nextInt();
                        System.out.print("Masukan Jumlah Stok             : "); ubah = input.nextInt();
                        stat.executeUpdate("update supplyBuah set Nama='"+buah+"' where kode='"+kode+"';");
                        stat.executeUpdate("update supplyBuah set tgl_exp='"+exp+"' where kode='"+kode+"';");
                        stat.executeUpdate("update supplyBuah set harga_PerKilo="+harga+" where kode='"+kode+"';");
                        stat.executeUpdate("update supplyBuah set stok="+ubah+" where kode='"+kode+"';");
                        stat.executeUpdate("update supplyBuah set kode='"+ubahkode+"' where kode='"+kode+"';");
                        break;

                        case 4:
                        System.out.println("Menghapus Supply ");
                        System.out.print("Masukan Kode yang ingin anda Hapus : "); kode = ganti.nextLine();
                        stat.executeUpdate("delete from supplyBuah where kode='"+kode+"';");

                        break;

                        case 5:
                        System.out.println("Menambah Data Baru");
                        System.out.print("Masukan Kode Baru    : "); ubahkode = ganti.nextLine();
                        System.out.print("Masukan Nama Baru    : "); buah = ganti.nextLine();
                        System.out.print("Masukan Stok Baru    : "); ubah = input.nextInt();
                        System.out.print("Masukan Harga Baru   : "); harga = input.nextInt();
                        System.out.print("Masukan Tgl Exp Baru : "); exp = ganti.nextLine();
                        stat.executeUpdate("insert into supplyBuah values('"+ubahkode+"','"+buah+"',"+ubah+","+harga+",'"+exp+"');");

                        break;
                    }
                    

                } while (pilih<6);
                
                } catch (Exception e) {
            System.err.println("Error : "+e.getMessage());
        }
    }
}
