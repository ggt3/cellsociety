package controller;
import java.util.HashMap;

import model.Cell;
import model.CellState;
import model.Grid;


public class TestSimulations {
	
	public Grid stupidMakeLifeGrid() {
		Grid newG = new Grid(8,8);
		Cell s = new Cell(CellState.ALIVE,new Packager());
		Cell t=new Cell(CellState.EMPTY, new Packager());
		for (int i = 0; i < 8; i++) {
			for (int k =0; k<8; k++) {
				if (i==1)
					newG.putCell(s, i, k);
				else
					newG.putCell(t, i, k);
			}
		}
		return newG;
	}
	
	public HashMap<String,String> stupidMap() {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("ALIVE", "RED");
		map.put("EMPTY", "BLACK");
		map.put("TREE", "AQUA");
		map.put("BURNING", "RED");
		return map;
	}
	
	public Packager createMap(HashMap<String,String> d) {
		Packager p = new Packager();
		p.setColorMap(d);
		return p;
	}
	//fire burning
	public Packager createProperties() {
		HashMap<String, Object> d = new HashMap<String, Object>();
		d.put("THRESHOLD", 0.50);
		Packager p = new Packager();
		p.setPropertiesMap(d);
		return p;
	}
	public Grid stupidMakeFireGrid() {
		Grid newG = new Grid(8,8);
		Cell s = new Cell(CellState.BURNING, createProperties());
		Cell t=new Cell(CellState.TREE, createProperties());
		for (int i = 0; i < 8; i++) {
			for (int k =0; k<8; k++) {
				if (i==1)
					newG.putCell(s, i, k);
				else
					newG.putCell(t, i, k);
			}
		}
		return newG;
	}
}
