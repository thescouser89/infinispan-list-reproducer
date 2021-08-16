import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class LocalFile {

    String a;
    String b;

    @ProtoFactory
    LocalFile(String a, String b) {
       this.a = a;
       this.b = b;
    }

    @ProtoField(value = 1, required = true)
    public String getA() {
        return a;
    }

    @ProtoField(value = 2, required = true)
    public String getB() {
        return b;
    }
}
