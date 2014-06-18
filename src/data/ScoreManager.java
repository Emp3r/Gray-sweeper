package data;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import utils.Rules;

public class ScoreManager {

	private String filePathEasy = "highscores/easy.xml";
	private String filePathMedium = "highscores/medium.xml";
	private String filePathHard = "highscores/hard.xml";
	private List<Record> listEasySmall,	listEasyMedium, listEasyLarge;
	private List<Record> listMediumSmall, listMediumMedium, listMediumLarge;
	private List<Record> listHardSmall, listHardMedium, listHardLarge;
	private int lowestEasySmall, lowestEasyMedium, lowestEasyLarge;
	private int lowestMediumSmall, lowestMediumMedium, lowestMediumLarge;
	private int lowestHardSmall, lowestHardMedium, lowestHardLarge;
	
	public ScoreManager() {
		this.listEasySmall = new ArrayList<Record>();
		this.listEasyMedium = new ArrayList<Record>();
		this.listEasyLarge = new ArrayList<Record>();
		this.listMediumSmall = new ArrayList<Record>();
		this.listMediumMedium = new ArrayList<Record>();
		this.listMediumLarge = new ArrayList<Record>();
		this.listHardSmall = new ArrayList<Record>();
		this.listHardMedium = new ArrayList<Record>();
		this.listHardLarge = new ArrayList<Record>();
		
		loadFile(Rules.EASY);
		loadFile(Rules.MEDIUM);
		loadFile(Rules.HARD);
	}
	
	public List<Record> getListEasy() {
		return listEasyMedium;
	}
	public List<Record> getListMedium() {
		return listMediumMedium;
	}
	public List<Record> getListHard() {
		return listHardMedium;
	}

	public List<List<Record>> getLists(int difficulty) {
		if (difficulty == Rules.EASY)
			return Arrays.asList(listEasySmall, listEasyMedium, listEasyLarge);
		else if (difficulty == Rules.MEDIUM)
			return Arrays.asList(listMediumSmall, listMediumMedium, listMediumLarge);
		else
			return Arrays.asList(listHardSmall, listHardMedium, listHardLarge);
	}

	public int getLowest(int difficulty, int size) {
		if (difficulty == Rules.EASY) {
			if (size == Rules.SIZE_SMALL) return lowestEasySmall;
			if (size == Rules.SIZE_MEDIUM) return lowestEasyMedium;
			else return lowestEasyLarge;
		}
		else if (difficulty == Rules.MEDIUM) {
			if (size == Rules.SIZE_SMALL) return lowestMediumSmall;
			if (size == Rules.SIZE_MEDIUM) return lowestMediumMedium;
			else return lowestMediumLarge;
		}
		else {
			if (size == Rules.SIZE_SMALL) return lowestHardSmall;
			if (size == Rules.SIZE_MEDIUM) return lowestHardMedium;
			else return lowestHardLarge;
		}
	}
	
	private String getFilePath(int difficulty) {
		if (difficulty == Rules.EASY)
			return filePathEasy;
		else if (difficulty == Rules.MEDIUM)
			return filePathMedium;
		else
			return filePathHard;
	}
	
	public void writeScore(int score, String name, int difficulty, int size) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		List<List<Record>> tables = getLists(difficulty);
		
		tables.get(size).set(9, new Record(score, name, dateFormat.format(date)));
		Collections.sort(tables.get(size), new RecordComparator());
		
		saveFile(difficulty);
	}
	
	private void saveFile(int difficulty) { 
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element all = doc.createElement("highscores");
			doc.appendChild(all);
			
			String sizeName = "small";
			for (List<Record> list : getLists(difficulty)) {
				Element dif = doc.createElement(sizeName);
				all.appendChild(dif);
				
				for (Record r : list) {
					Element rec = doc.createElement("record");
					Element score = doc.createElement("score");
					score.setTextContent(String.valueOf(r.score));
					rec.appendChild(score);
					Element player = doc.createElement("player");
					player.setTextContent(r.player);
					rec.appendChild(player);
					Element date = doc.createElement("date");
					date.setTextContent(r.date);
					rec.appendChild(date);
					dif.appendChild(rec);
				}
				if (sizeName.equals("medium"))
					sizeName = "large";
				else
					sizeName = "medium";
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(getFilePath(difficulty)));
			transformer.transform(source, result);
		} 
		catch (Exception e) { }
	}
	
	private void loadFile(int difficulty) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new FileInputStream(new File(getFilePath(difficulty))));
			Element all = doc.getDocumentElement();
			
			int i = 0;
			for (List<Record> list : getLists(difficulty)) {
				Node size = all.getChildNodes().item(i);
				
				for (int j = 0; j < size.getChildNodes().getLength(); j++) {
					Node rec = size.getChildNodes().item(j);
					
					int score = Integer.parseInt(rec.getChildNodes().item(0).getTextContent());
					String player = rec.getChildNodes().item(1).getTextContent();
					String date = rec.getChildNodes().item(2).getTextContent();
					
					list.add(new Record(score, player, date));
				}
				i++;
			}
		} catch (Exception e) { 
			fillTables(difficulty);
			saveFile(difficulty);
		}
	}
	
	public void fillTables(int difficulty) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		for (List<Record> list : getLists(difficulty)) {
			for (int i = 0; i < 10; i++) {
				list.add(new Record(9999, "?", dateFormat.format(new Date())));
			}
		}
		saveFile(difficulty);
	}
	
	public void resetTables() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		for (List<Record> list : Arrays.asList(listEasySmall, listEasyMedium, listEasyLarge, 
											   listMediumSmall, listMediumMedium, listMediumLarge,
											   listHardSmall, listHardMedium, listHardLarge)) {
			for (int i = 0; i < 10; i++) {
				list.set(i, new Record(9999, "?", dateFormat.format(new Date())));
			}
		}
		saveFile(Rules.EASY);
		saveFile(Rules.MEDIUM);
		saveFile(Rules.HARD);
	}
	
	private class RecordComparator implements Comparator<Record> {
		@Override
		public int compare(Record r1, Record r2) {
			return r1.score - r2.score;
		}
	}
}
