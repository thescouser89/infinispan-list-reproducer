import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.List;

public class ListWrapper {
    List<LocalFile> data;

    @ProtoFactory
    public ListWrapper(List<LocalFile> data) {
        this.data = data;
    }

    @ProtoField(value = 1)
    List<LocalFile> getData() {
        return this.data;
    }
}
