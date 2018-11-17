/**
 * @Title:PDA.java
 * @Description:TODO
 * @author:zgw
 * @date:2018年10月10日下午6:59:59 
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


/**
 * @author zgw
 *
 */
public class PDA {
	public static void main(String[] args) {
		System.out.println("输入字符串【仅可包含abc】,判断b的数量是否和c的数量相同，相同接受，不同拒绝");
		//将字符存入字符数组中
		Scanner sc = new Scanner(System.in);
		String str=sc.next();
		str="#"+str;//特殊处理，用来使栈中刚开始识别输入串时栈中栈顶符号为$
		char cin[] = str.toCharArray();//利用toCharArray方法转换
		sc.close();
		//初始化关系集合
		ArrayList<Old2NewRelation> list=initRelation();
		Stack stack=new Stack();
		String strState="q1";
		String endState="q7";
		System.out.println("PDA的初始状态为："+strState+"结束接收状态为："+endState);
		System.out.println("------------------------------------");
		boolean isTrave=false;
		//开始处理逐个识别字符，bc中需要选择一个进行压栈，此处选择对b进行操作
		for (int i = 0; i < cin.length; i++) {
			char cNow = cin[i];
			if (cNow!='#') {
				System.out.printf("识别第%d个字符%s",i,cNow);
				System.out.println();
			}else{
				System.out.println("预制特殊标志$");
				System.out.println("------------------------------------");
			}			
			isTrave=false;
			for (Old2NewRelation stateTr : list) {
				if (isTrave) {//状态发生转移之后，就不再遍历，而是对下一个字符重新开始判断
					break;
				}
				if(strState.equals(stateTr.getOldState())&&(cNow==stateTr.getTrans())) {
					//如果栈空或栈顶字符和当前识别的字符一致(stack.empty()栈的特殊处理也归到这一逻辑中)
					if (stack.empty()||((char)stack.peek())=='$'||((char)stack.peek())==cNow) {			
						if (stateTr.getTop()=='#') {
							strState=stateTr.getNewState();//状态转移到新状态
							System.out.println(stateTr.toString());
							isTrave=true;
							if (stateTr.getPutInStack()!='#') {//不管栈是否为空，只要是非空替换空，就入栈
								//非空替换空的状态，也就是要压入栈中一个字符								
								System.out.println("将其压入栈中"+stateTr.getPutInStack());
								System.out.println("------------------------------------");
								stack.push(stateTr.getPutInStack());
							}else {
								System.out.println("用空替换空，不对栈进行操作");
								System.out.println("------------------------------------");
							}
						}						
					}else {//栈中有元素，且和输入字符不同
						strState=stateTr.getNewState();//状态转移到新状态
						System.out.println(stateTr.toString());
						isTrave=true;
						if (stateTr.getTop()!='#') {
							System.out.println("出栈元素为："+stack.peek());
							System.out.println("------------------------------------");
							stack.pop();
						}else {
							System.out.println("栈顶从空到空，不对栈进行操作");
							System.out.println("------------------------------------");
						}
						
					}
				}
			}
		}
		isTrave=false;
		if (!stack.empty()&&((char)stack.peek())=='$') {
			//根据状态转移函数得到最终处理的状态
			for (Old2NewRelation stateTr : list) {
				if (isTrave) {
					break;
				}
				if(stateTr.getTop()=='$'&&strState.equals(stateTr.getOldState())&&('#'==stateTr.getTrans())) {
					isTrave=true;
					strState=stateTr.getNewState();//状态转移到新状态
					System.out.println("当前栈顶字符为："+stack.peek()+"对该标志处理，进行状态转移");
					System.out.println(stateTr.toString());
					System.out.println("------------------------------------");
				}
			}
		}else {
			System.out.println("字符串全部识别后，栈顶字符为:"+stack.peek()+"不是预制的栈标志$");
			System.out.println("------------------------------------");
		}
		if (strState==endState) {
			System.out.println("字符b和c在字符串中出现的次数相同，能被该PDA正确接受，字符串全部识别完成后在最终状态"+strState);
		} else {
			System.out.println("字符b和c在字符串中出现的次数不同，被该PDA拒绝，字符串全部识别结束后当前状态为"+strState);
		}
	}

	/**
	 * 初始化关系集合
	 * @return
	 */
	private static ArrayList<Old2NewRelation> initRelation() {
		//用‘#’表示空字符
		//分别表示 当前状态-识别字符-转移状态-栈顶元素-替换栈顶的元素
		ArrayList<Old2NewRelation> list = new ArrayList<Old2NewRelation>();
		list.add(new Old2NewRelation("q1",'#',"q2",'#','$'));
		list.add(new Old2NewRelation("q2",'a',"q4",'#','#'));
		list.add(new Old2NewRelation("q2",'b',"q2",'#','b'));
		list.add(new Old2NewRelation("q2",'c',"q3",'b','#'));
		list.add(new Old2NewRelation("q2",'c',"q6",'#','c'));
		list.add(new Old2NewRelation("q2",'#',"q7",'$','#'));
		list.add(new Old2NewRelation("q3",'a',"q4",'#','#'));
		list.add(new Old2NewRelation("q3",'b',"q2",'#','b'));
		list.add(new Old2NewRelation("q3",'c',"q3",'b','#'));
		list.add(new Old2NewRelation("q3",'c',"q6",'#','c'));
		list.add(new Old2NewRelation("q3",'#',"q7",'$','#'));
		list.add(new Old2NewRelation("q4",'a',"q4",'#','#'));
		list.add(new Old2NewRelation("q4",'b',"q5",'c','#'));
		list.add(new Old2NewRelation("q4",'b',"q2",'#','b'));
		list.add(new Old2NewRelation("q4",'c',"q3",'b','#'));
		list.add(new Old2NewRelation("q4",'c',"q6",'#','c'));
		list.add(new Old2NewRelation("q4",'#',"q7",'$','#'));
		list.add(new Old2NewRelation("q5",'a',"q4",'#','#'));
		list.add(new Old2NewRelation("q5",'b',"q5",'c','#'));
		list.add(new Old2NewRelation("q5",'b',"q2",'#','b'));
		list.add(new Old2NewRelation("q5",'c',"q6",'#','c'));
		list.add(new Old2NewRelation("q5",'#',"q7",'$','#'));
		list.add(new Old2NewRelation("q6",'a',"q4",'#','#'));
		list.add(new Old2NewRelation("q6",'b',"q5",'c','#'));
		list.add(new Old2NewRelation("q6",'c',"q6",'#','c'));
		list.add(new Old2NewRelation("q6",'#',"q7",'$','#'));
		return list;
	}
}
