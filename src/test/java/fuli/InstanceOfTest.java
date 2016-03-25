package fuli;


public class InstanceOfTest {
	
	
	public static class TestClass extends InstanceOfTest{
		
		
	}
	
	
	public static void main(String[] args){
		
         TestClass testClass = new TestClass();		
		 System.out.println(testClass instanceof InstanceOfTest);
         
	}

}
