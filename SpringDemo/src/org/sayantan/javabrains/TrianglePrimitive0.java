package org.sayantan.javabrains;

public class TrianglePrimitive0 {
	private String type;
	private int height;
	
	public void draw(){
		System.out.println(getType() +  " Triangle drawn with height = "+ getHeight());
	}
	
	// Using getter-setter, setter can be alternatively replaced by constructor, 
	// then spring.xml also needs to be changed
	public String getType() {
		return type;
	}
	
	//	public void setType(String type) {
	//			this.type = type;
	//	}
	
	public TrianglePrimitive0(String type){
		this.type = type;
	}
	public TrianglePrimitive0 (int ht) {
		this.height = ht ;
	}
	public TrianglePrimitive0 (String type, int height){
		this.type = type;
		this.height = height ;
	}
	
	public int getHeight() {
		return height;
	}
}