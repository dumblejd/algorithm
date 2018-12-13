package algorithmtest;

public class AVLNode<T extends Comparable> {

    public AVLNode<T> left;//左结点

    public AVLNode<T> right;//右结点

    public T data;

    public int height;//当前结点的高度

    public AVLNode(T data) {
        this(null,null,data);
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
        this(left,right,data,0);
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data, int height) {
        this.left=left;
        this.right=right;
        this.data=data;
        this.height = height;
    }
    public int height(AVLNode t)
    {
    	if(t==null)
    	{
    		return -1;
    	}
		return t.height;
    	
    }
    private AVLNode<T> singleRotateRight(AVLNode<T> w){

        AVLNode<T> x=w.right;

        w.right=x.left;
        x.left=w;

        //重新计算x/w的高度
        w.height=Math.max(height(w.left),height(w.right))+1;
        x.height=Math.max(height(x.left),w.height)+1;

        //返回新的根结点
        return x;
    }
    private AVLNode<T> singleRotateLeft(AVLNode<T> x){
        //把w结点旋转为根结点
        AVLNode<T> w=  x.left;
        //同时w的右子树变为x的左子树
        x.left=w.right;
        //x变为w的右子树
        w.right=x;
        //重新计算x/w的高度
        x.height=Math.max(height(x.left),height(x.right))+1;
        w.height=Math.max(height(w.left),x.height)+1;
        return w;//返回新的根结点
    }
  
    private AVLNode<T> doubleRotateWithLeft(AVLNode<T> x){
        //w先进行RR旋转
        x.left=singleRotateRight(x.left);
        //再进行x的LL旋转
        return singleRotateLeft(x);
    }
    private AVLNode<T> doubleRotateWithRight(AVLNode<T> x){
        //先进行LL旋转
        x.right=singleRotateLeft(x.right);
        //再进行RR旋转
        return singleRotateRight(x);
    }
    public void remove(T data) {
        if (data==null){
            throw new RuntimeException("data can\'t not be null ");
        }
        //this.root=remove(data,root);
    }

    /**
     * 删除操作
     * @param data
     * @param p
     * @return
     */
    public AVLNode<T> remove(T data,AVLNode<T> p){

        if(p ==null)
            return null;

        int result=data.compareTo(p.data);

        //从左子树查找需要删除的元素
        if(result<0){
            p.left=remove(data,p.left);

            //检测是否平衡
            if(height(p.right)-height(p.left)==2){
                AVLNode<T> currentNode=p.right;
                //判断需要那种旋转
                if(height(currentNode.left)>height(currentNode.right)){
                    //LL
                    p=singleRotateLeft(p);
                }else{
                    //LR
                    p=doubleRotateWithLeft(p);
                }
            }

        }
        //从右子树查找需要删除的元素
        else if(result>0){
            p.right=remove(data,p.right);
            //检测是否平衡
            if(height(p.left)-height(p.right)==2){
                AVLNode<T> currentNode=p.left;
                //判断需要那种旋转
                if(height(currentNode.right)>height(currentNode.left)){
                    //RR
                    p=singleRotateRight(p);
                }else{
                    //RL
                    p=doubleRotateWithRight(p);
                }
            }
        }
        //已找到需要删除的元素,并且要删除的结点拥有两个子节点
        else if(p.right!=null&&p.left!=null){

            //寻找替换结点
            //p.data=findMin(p.right).data;

            //移除用于替换的结点
            p.right = remove( p.data, p.right );
        }
        else {
            //只有一个孩子结点或者只是叶子结点的情况
            p=(p.left!=null)? p.left:p.right;
        }

        //更新高度值
        if(p!=null)
            p.height = Math.max( height( p.left ), height( p.right ) ) + 1;
        return p;
    }
    public static void main(String[] args) {
    	AVLNode n1 = new AVLNode<Integer>(1);
    	AVLNode n2 = new AVLNode<Integer>(2);
    	AVLNode n3 = new AVLNode<Integer>(3);
    	AVLNode n4 = new AVLNode<Integer>(4);
    	AVLNode n5 = new AVLNode<Integer>(5);
    	AVLNode n6 = new AVLNode<Integer>(6);
    	AVLNode n7 = new AVLNode<Integer>(7);
    	AVLNode n8 = new AVLNode<Integer>(8);
    	
    	n3.left=n2; n3.height=3;
    	n2.left=n1;n2.height=1;
    	n3.right=n7;n1.height=-1;
    	n7.left=n5;n7.height=2;
    	n7.right=n8;n8.height=-1;n4.height=-1;n6.height=-1;n5.height=1;
    	n5.left=n4;
    	n5.right=n6;
    	n3.remove(1,n3);
	}
}

