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
		map.put("X", "BLUE");
		map.put("Y", "RED");
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
		d.put("THRESHOLD", 0.4);
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
	public Grid stupidMakeSegGrid(int size) {
		Grid newG = new Grid(size,size);
		Cell s = new Cell(CellState.X, createProperties());
		Cell t =new Cell(CellState.Y, createProperties());
		Cell x = new Cell(CellState.EMPTY, createProperties());
		for (int i = 0; i < size; i++) {
			for (int k =0; k<size; k++) {
				
				if ((i==1&&k==4) || (i==5 &&k==6))
					newG.putCell(x, i, k);
				else if (i == 0||i==1||i==9 ||i==8)
					newG.putCell(s, i, k);
				else if( i == 4||i==6||i==2||i==3)
					newG.putCell(t,i,k);
				else
					newG.putCell(x, i, k);
			}
		}
		return newG;
	}
}
