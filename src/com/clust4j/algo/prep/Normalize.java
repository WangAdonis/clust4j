package com.clust4j.algo.prep;

import org.apache.commons.math3.linear.AbstractRealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import com.clust4j.utils.MatUtils;
import com.clust4j.utils.VecUtils;

public enum Normalize implements PreProcessor {
	MEAN_CENTER {
		@Override
		public PreProcessor copy() { return this; }

		@Override
		public AbstractRealMatrix operate(AbstractRealMatrix data) {
			return new Array2DRowRealMatrix(operate(data.getData()), false);
		}

		@Override
		public double[][] operate(double[][] data) {
			MatUtils.checkDims(data);
			final double[][] copy = MatUtils.copyMatrix(data);
			final int n = data[0].length;
			
			for(int col = 0; col < n; col++) {
				final double[] v = MatUtils.getColumn(copy, col);
				MatUtils.setColumnInPlace(copy, col, VecUtils.scalarSubtract(v, VecUtils.mean(v)));
			}
			
			return copy;
		}
		
	},
	
	CENTER_SCALE {
		@Override
		public PreProcessor copy() { return this; }

		@Override
		public AbstractRealMatrix operate(AbstractRealMatrix data) {
			return new Array2DRowRealMatrix(operate(data.getData()), false);
		}

		@Override
		public double[][] operate(double[][] data) {
			MatUtils.checkDims(data);
			final double[][] copy = MatUtils.copyMatrix(data);
			final int m = data.length, n = data[0].length;
			
			for(int col = 0; col < n; col++) {
				final double[] v = MatUtils.getColumn(copy, col);
				final double mean = VecUtils.mean(v);
				final double sd = VecUtils.stdDev(v, mean);
				
				for(int row = 0; row < m; row++) {
					final double new_val = (v[row] - mean) / sd;
					copy[row][col] = new_val;
				}
			}
			
			return copy;
		}
		
	}
}
