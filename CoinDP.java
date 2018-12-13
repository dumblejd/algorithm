package algorithmtest;



public class CoinDP {    
	/**       * 动态规划算法      
	 *  * @param values:	 保存所有币值的数组       
	 *  * @param valueKinds:硬币种类       
	 *  * @param money:	 金额       
	 *  * @param minCoins:  保存所有金额所需的最小硬币数      
	 *  */     
	public static void dp(int[] values, int money, int[] minCoins) 
	{  		int valueKinds = values.length;        minCoins[0] = 0;        // 保存1元、2元、3元、……、money元所需的最小硬币数          
	for (int sum = 1; sum <= money; sum++) {              
		// 使用最小币值，需要的硬币数量是最多的            
		int min = sum;             
		// 遍历每一种面值的硬币            
		for (int kind = 0; kind < valueKinds; kind++) {                  
			// 若当前面值的硬币小于总额则分解问题并查表              
			if (values[kind] <= sum) {                     
				int temp = minCoins[sum - values[kind]] + 1;               
				if (temp < min) 
				{                    
					min = temp;                   
				}                  
				}  
				else 
				{                   
					break;     
					}          
			}            
		// 保存最小硬币数          
		minCoins[sum] = min;     
		System.out.println("面值为 " + (sum) + " 的最小硬币数 : " + minCoins[sum]);          
		}     
	}
	public static void main(String[] args) {         
		// 硬币面值预先已经按升序排列       
		int[] coinValue = new int[] {1,3,5,11};  		
		// 需要的金额(15用动态规划得到的是3（5+5+5），用贪心得到的是5（11+1+1+1+1）       
		int money = 20;          // 保存每一个金额所需的最小硬币数，0号单元舍弃不用，所以要多加1         
		int[] coinsUsed = new int[money + 1];          
		dp(coinValue, money, coinsUsed);      
		}  
	}


