package cn.codethink.xiaoming.expression.util;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.annotation.Analyser;
import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.annotation.Type;
import cn.codethink.xiaoming.expression.interpreter.ConfigurableInterpreter;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.NoSuchElementException;

public class ConfigurableInterpreters {
    private ConfigurableInterpreters() {
        throw new NoSuchElementException("No " + ConfigurableInterpreters.class.getName() + " instances for you!");
    }
    
    private static final ConfigurableInterpreter INSTANCE = ConfigurableInterpreter.newInstance(Interpreter.getInstance());
    static {
        INSTANCE.registerTypes(new JDKType());
    }
    
    private static class JDKType {
    
        @Type
        @Constructor
        public URL constructURL(String url) throws MalformedURLException {
            return new URL(url);
        }
        
        @Analyser
        @Type(URL.class)
        public Expression analyzeURL(URL url, Interpreter interpreter) {
            return interpreter.compile("URL(\"" + StringEscapeUtils.escapeJava(url.toString()) + "\")");
        }
        
        @Type
        @Constructor
        public File constructFile(String path) {
            return new File(path);
        }
        
        @Type
        @Constructor
        public File constructFile(File parent, String path) {
            return new File(parent, path);
        }
        
        @Type(File.class)
        @Analyser
        public Expression analyzeFile(File file, Interpreter interpreter, AnalyzingConfiguration configuration) {
            return interpreter.compile("File(\"" + StringEscapeUtils.escapeJava(file.getName()) + "\")");
        }
        
        @Constructor
        @Type(value = byte[].class, name = "Bytes")
        public byte[] constructBytes(String base64) {
            return Base64.getDecoder().decode(base64);
        }
    
        @Analyser
        @Type(value = byte[].class, name = "Bytes")
        public Expression analyzeBytes(byte[] bytes, Interpreter interpreter) {
            return interpreter.compile("Bytes(\"" + StringEscapeUtils.escapeJava(Base64.getEncoder().encodeToString(bytes)) + "\")");
        }
    }
    
    public static ConfigurableInterpreter getInstance() {
        return INSTANCE;
    }
}
