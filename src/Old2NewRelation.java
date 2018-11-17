/**
 * @Title:Old2NewRelation.java
 * @Description:TODO
 * @author:zgw
 * @date:2018年9月26日下午8:07:24 
 */

/**
 * @author zgw
 *
 */
public class Old2NewRelation {

	private String oldState;//当前状态
	private char trans;//读取的字符
	private String newState;//新状态
	private char top;//栈顶字符
	private char putInStack;//要压入栈中的字符
	
	/**
	 * @return oldState
	 */
	public String getOldState() {
		return oldState;
	}
	/**
	 * @param oldState 要设置的 oldState
	 */
	public void setOldState(String oldState) {
		this.oldState = oldState;
	}
	/**
	 * @return trans
	 */
	public char getTrans() {
		return trans;
	}
	/**
	 * @param trans 要设置的 trans
	 */
	public void setTrans(char trans) {
		this.trans = trans;
	}
	/**
	 * @return newState
	 */
	public String getNewState() {
		return newState;
	}
	/**
	 * @param newState 要设置的 newState
	 */
	public void setNewState(String newState) {
		this.newState = newState;
	}

	/**
	 * @param oldState
	 * @param trans
	 * @param newState
	 * @param top
	 * @param putInStack
	 */
	public Old2NewRelation(String oldState, char trans, String newState, char top, char putInStack) {
		super();
		this.oldState = oldState;
		this.trans = trans;
		this.newState = newState;
		this.top = top;
		this.putInStack = putInStack;
	}
	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "状态转移关系 [当前状态：" + oldState + ", 识别的字符：" + trans + ", 转移到的状态：" + newState + ", 对栈的操作是用：" + putInStack
				+ "替换栈顶元素：" + top + "]";
	}
	/**
	 * @return putInStack
	 */
	public char getPutInStack() {
		return putInStack;
	}
	/**
	 * @param putInStack 要设置的 putInStack
	 */
	public void setPutInStack(char putInStack) {
		this.putInStack = putInStack;
	}
	/**
	 * @return top
	 */
	public char getTop() {
		return top;
	}
	/**
	 * @param top 要设置的 top
	 */
	public void setTop(char top) {
		this.top = top;
	}
	
}
