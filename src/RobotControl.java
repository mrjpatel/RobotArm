import javax.swing.*;

class RobotControl {
	private Robot r;

	public RobotControl(Robot r) {
		this.r = r;
	}

	public void control(int barHeights[], int blockHeights[]) {
		
		//Defining all the required integers
		int h = 2;
		int w = 1;
		int d = 0;
		
		int targetcolumn1 = 0;
		int targetcolumn2 = 0;
		int target = 3;
		
		int sourcecol = 10;
		int barcount = 0;
		int totalht = 0;
		
		//Assign array to read from backwards
		int currentblock = blockHeights.length - 1; 
		
		int sourceht = 0;
		//Calculating total height of source column
		for (int currentht = 0; currentht < blockHeights.length; currentht++)	
		{
			sourceht += blockHeights[currentht];
		}
		
		
		while (currentblock >= 0)
		{
			//raising depth (third arm) in case if it is not 0
			while (d > 0)				
			{
				r.raise();
				d--;
			}
			int currentbar = barHeights[barcount];
			//read array from backward
			int blockht = blockHeights[currentblock];		
		
			//calculating the largest bar height
			int barlarge = barHeights[0];					
			for(int i=1; i< barHeights.length; i++)
				{
					if(barHeights[i] > barlarge)     
						barlarge = barHeights[i];               
				}
        
			//calculating the largest block height
			int blocklarge = blockHeights[0];				
			for(int j=1; j< blockHeights.length; j++)
				{
					if(blockHeights[j] > blocklarge)
						blocklarge = blockHeights[j];               
				}
			
			//finding the required height for first arm to go upwards
			if (sourceht < (blocklarge + barlarge))		
				{
					totalht = blocklarge + barlarge + 2;
				}
			else 
				{
					totalht = sourceht;
				}
		
			//raising the first arm	
			while (h - 1 < totalht)			
				{
					r.up();
					h++;
				}
			//recalculating the max height required
			totalht =- blockht;				
			//extending the arm till the source column	
			while (w < sourcecol)	
				{
					r.extend();
					w++;
				}
			//lowering the arm till the available block
			while(h - d > sourceht + 1)		
				{
					r.lower();
					d++;
				}
		
			r.pick();
		
			//recalculating the source height after the block is picked up
			sourceht-=blockht;			
		
			//raising the third arm(depth) in case the bars heights is greater than source height
			while(d > 0)				
			{
				r.raise();
				d--;
			}

		//locating the size of blocks to their destination
			if (blockHeights[currentblock] == 1) 
				{
				//contracting the arm with to the destination
					while (w > 1) 
						{
							r.contract();
							w--;
						}
					//lowering the arm with the block to destination
					while ((h - 1) - d - blockht > targetcolumn1) 
						{
							r.lower();
							d++;
						}
					r.drop();
					targetcolumn1++;
				}
			else if (blockHeights[currentblock] == 2)
				{
				//contracting the arm with to the destination
					while (w > 2) 
						{
							r.contract();
							w--;
						}
					//lowering the arm with the block to destination
					while ((h - 1) - d - blockht > targetcolumn2) 
						{
							r.lower();
							d++;
						}
					r.drop();
					targetcolumn2++;
					targetcolumn2++;
				}
			else
				{
				//contracting the arm with to the destination
					while (w > target) 
						{
							r.contract();
							w--;
						}
					//lowering the arm with the block to destination
					while ((h - 1) - d - blockht > currentbar) 
						{
							r.lower();
							d++;
						}
					r.drop();
					target++;
					barcount++;
				
				}
			//decrementing the current block so that the loops soen't go to infinity
			currentblock--;
		}
		
		
		
	}

	public void sampleControlMechanism(int barHeights[], int blockHeights[]) {

	}

	public void controlMechanismForScenarioA(int barHeights[], int blockHeights[]) {

	}

	public void controlMechanismForScenarioB(int barHeights[], int blockHeights[]) {

	}

	public void controlMechanismForScenarioC(int barHeights[], int blockHeights[]) {

	}

}
