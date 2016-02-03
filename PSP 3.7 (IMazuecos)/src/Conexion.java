import com.db4o.*;

public class Conexion {
    final static String BDPer = "EMPLEDEP.YAP";
    static ObjectContainer db;
    static{
        db = Db4o.openFile(Db4o.newConfiguration(), BDPer);
    }
    public static ObjectContainer getDBConexion(){
        return db;
    }
}