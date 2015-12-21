package com.clust4j.algo.prep;

import static org.junit.Assert.*;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import com.clust4j.utils.MatUtils;
import com.clust4j.utils.VecUtils;

public class PreProcessorTests {

	@Test
	public void testMeanCenter() {
		final double[][] data = new double[][] {
			new double[] {0.005, 	 0.182751,  0.1284},
			new double[] {3.65816,   0.29518,   2.123316},
			new double[] {4.1234,    0.27395,   1.8900002}
		};
		
		final double[][] operated = Normalize.MEAN_CENTER.operate(data);
		assertTrue(Precision.equals(VecUtils.mean(MatUtils.getColumn(operated, 0)), 0, Precision.EPSILON));
		assertTrue(Precision.equals(VecUtils.mean(MatUtils.getColumn(operated, 1)), 0, Precision.EPSILON));
		assertTrue(Precision.equals(VecUtils.mean(MatUtils.getColumn(operated, 2)), 0, Precision.EPSILON));
	}
	
	@Test
	public void testCenterScale() {
		final double[][] data = new double[][] {
			new double[] {0.005, 	 0.182751,  0.1284},
			new double[] {3.65816,   0.29518,   2.123316},
			new double[] {4.1234,    0.27395,   1.8900002}
		};
		
		final double[][] operated = Normalize.CENTER_SCALE.operate(data);
		assertTrue(Precision.equals(VecUtils.mean(MatUtils.getColumn(operated, 0)), 0, 1e-12));
		assertTrue(Precision.equals(VecUtils.mean(MatUtils.getColumn(operated, 1)), 0, 1e-12));
		assertTrue(Precision.equals(VecUtils.mean(MatUtils.getColumn(operated, 2)), 0, 1e-12));
		

		assertTrue(Precision.equals(VecUtils.stdDev(MatUtils.getColumn(operated, 0)), 1, Precision.EPSILON));
		assertTrue(Precision.equals(VecUtils.stdDev(MatUtils.getColumn(operated, 1)), 1, Precision.EPSILON));
		assertTrue(Precision.equals(VecUtils.stdDev(MatUtils.getColumn(operated, 2)), 1, Precision.EPSILON));
	}

}
