import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
	
public class BuildClassTest {
		
		private String className = "Var";
		private Var var1 = new Var("String", "typeOfVar" );
		private Var var2 = new Var("String", "nameOfVar" );
		private ArrayList <Var> classFields = new ArrayList <Var>(Arrays.asList(var1, var2));
		private BuildClass varClass = new BuildClass(className, classFields);
		
		@Test
		public void test1() {
			
			String expected = "private String typeOfVar;\nprivate String nameOfVar;\n";
			expected = expected.replaceAll("(?m)^", "\t");
			
			String actual = varClass.makeFields();
			
			assertEquals(expected, actual);
			
		}
		
		@Test
		public void test2() {
			
			String expected = "public Var (String typeOfVar, String nameOfVar) {\n    "
					+ "this.typeOfVar = typeOfVar;\n    this.nameOfVar = nameOfVar;\n}"; 
			expected = expected.replaceAll("(?m)^", "\t");
			
			String actual = varClass.makeConstructor();
			
			assertEquals(expected, actual);
			
		}
		
		@Test
		public void test3() {
			
			String expected = "public String getTypeOfVar () {\n    return typeOfVar;"
					+ "\n}\n\npublic String getNameOfVar () {\n    return nameOfVar;\n}\n\n";
			expected = expected.replaceAll("(?m)^", "\t");
			
			String actual = varClass.makeGetters();
			
			assertEquals(expected, actual);
		}
		
		@Test
		public void test4() {
			
			String expected = "public void setTypeOfVar (String typeOfVar) {\n    "
					+ "this.typeOfVar = typeOfVar;\n}\n\npublic void setNameOfVar (String nameOfVar) "
					+ "{\n    this.nameOfVar = nameOfVar;\n}\n\n";
			expected = expected.replaceAll("(?m)^", "\t");
			
			String actual = varClass.makeSetters();
			
			assertEquals(expected, actual);
		}
		
		@Test
		public void test5() {
			
			String expectedFields = "private String typeOfVar;\nprivate String nameOfVar;\n";
			expectedFields = expectedFields.replaceAll("(?m)^", "\t");
			
			String expectedConstructor = "public Var (String typeOfVar, String nameOfVar) {\n    "
					+ "this.typeOfVar = typeOfVar;\n    this.nameOfVar = nameOfVar;\n}"; 
			expectedConstructor = expectedConstructor.replaceAll("(?m)^", "\t");
			
			String expectedGetters = "public String getTypeOfVar () {\n    return typeOfVar;"
					+ "\n}\n\npublic String getNameOfVar () {\n    return nameOfVar;\n}\n\n";
			expectedGetters = expectedGetters.replaceAll("(?m)^", "\t");
			
			String expectedSetters = "public void setTypeOfVar (String typeOfVar) {\n    "
					+ "this.typeOfVar = typeOfVar;\n}\n\npublic void setNameOfVar (String nameOfVar) "
					+ "{\n    this.nameOfVar = nameOfVar;\n}\n\n";
			expectedSetters = expectedSetters.replaceAll("(?m)^", "\t");
			
			String expected = "public class Var {\n\n" + expectedFields + "\n" + expectedConstructor +
					"\n\n" + expectedGetters + expectedSetters +"}";
			
			String actual = varClass.buildClass();
			assertEquals(expected, actual);
		}

}



