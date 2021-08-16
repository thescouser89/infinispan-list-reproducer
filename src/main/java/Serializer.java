import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
      includeClasses = {
              LocalFile.class,
              ListWrapper.class,
      },
      schemaFileName = "buildfinder.proto",
      schemaFilePath = "proto/",
      schemaPackageName = "test")
interface Serializer extends GeneratedSchema {
}