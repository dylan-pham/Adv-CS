public class Node<E>{
	private E data;
	private Node<E> parent;
	private Node<E> left;
	private Node<E> right;
  
	public Node(E data){
		this.data = data;
	  	parent = null;
	  	left = null;
	  	right = null;
	}
  
	public E get(){
	  	return data;
	}
  
	public void setData(E data) {
	  	this.data = data;
	}
  
	public Node<E> getLeft(){
	  	return left;
	}
  
	public Node<E> getRight(){
	  	return right;
	}
  
	public Node<E> getParent(){
	  	return parent;
	}
  
	public void set(E data){
	  	this.data = data;
	}
  
	public void setParent(Node<E> node){
	  	parent = node;
	}
  
	public void setLeft(Node<E> node){
		left = node;
	}
  
	public void setRight(Node<E> node){
	  	right = node;
	}
  
	// public String checkNodes(){
	// 		if(left == null){
	// 			if(right == null){
	// 				return "none";
	// 		  }
	// 		  else if(right != null){
	// 			  return "right";
	// 		  }
	// 	  }
	// 	  else{
	// 		  if(right == null){
	// 			  return "left";
	// 		  }
	// 		  else if(right != null){
	// 			  return "both";
	// 		  }
	// 	  }
		  
	// 	  return null;
  
	public String toString(){
	  return data.toString();
	}
}
