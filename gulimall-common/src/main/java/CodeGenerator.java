import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

public class CodeGenerator {


    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/gulimall_wms", "root", "root")
                .globalConfig(builder -> {
                    builder.author("tifa") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D://"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.atguigu.gulimall.ware") // 设置父包名
                                .pathInfo(Collections.singletonMap(OutputFile.controller, "D:\\Code\\GuliMall\\gulimall-ware\\src\\main\\java\\com\\atguigu\\gulimall\\ware\\controller"))
                )
                .strategyConfig(builder ->
                        builder.addInclude() // 设置需要生成的表名
                                .addTablePrefix("wms_") // 设置过滤表前缀
                                .serviceBuilder().formatServiceFileName("%sService")
                                .controllerBuilder().enableRestStyle().enableFileOverride()
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
