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

public class ScoreManager {
	
	private String filePath = "highscores/highscores.xml";
	private List<Record> listEasy;
	private List<Record> listMedium;
	private List<Record> listHard;
	private int lowestEasy;
	private int lowestMedium;
	private int lowestHard;
	
	public ScoreManager() {
		this.listEasy = new ArrayList<Record>();
		this.listMedium = new ArrayList<Record>();
		this.listHard = new ArrayList<Record>();
		
		loadFile();
	}
	
	public List<Record> getListEasy() {
		return listEasy;
	}
	public List<Record> getListMedium() {
		return listMedium;
	}
	public List<Record> getListHard() {
		return listHard;
	}
	public int getLowestEasy() {
		return lowestEasy;
	}
	public int getLowestMedium() {
		return lowestMedium;
	}
	public int getLowestHard() {
		return lowestHard;
	}
	
	public void writeScore(int score, String name, int difficulty) {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		if (difficulty == 13) {
			if (score > lowestEasy) {
				listEasy.set(9, new Record(score, name, dateFormat.format(date)));
				Collections.sort(listEasy, new RecordComparator());
			}
		} else if (difficulty == 8) {
			if (score > lowestMedium) {
				listMedium.set(9, new Record(score, name, dateFormat.format(date)));
				Collections.sort(listMedium, new RecordComparator());
			}
		} else if (difficulty == 5) {
			if (score > lowestHard) {
				listHard.set(9, new Record(score, name, dateFormat.format(date)));
				Collections.sort(listHard, new RecordComparator());
			}
		}
		saveFile();
	}
	
	private void saveFile() { 
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element all = doc.createElement("highscores");
			doc.appendChild(all);
			
			String difName = "easy";
			for (List<Record> list : Arrays.asList(listEasy, listMedium, listHard)) {
				Element dif = doc.createElement(difName);
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
				if (difName.equals("medium"))
					difName = "hard";
				else
					difName = "medium";
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
		} 
		catch (Exception e) { }
	}
	
	private void loadFile() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new FileInputStream(new File(filePath)));
			Element all = doc.getDocumentElement();
			
			int i = 0;
			for (List<Record> list : Arrays.asList(listEasy, listMedium, listHard)) {
				Node dif = all.getChildNodes().item(i);
				
				for (int j = 0; j < dif.getChildNodes().getLength(); j++) {
					Node rec = dif.getChildNodes().item(j);
					
					int score = Integer.parseInt(rec.getChildNodes().item(0).getTextContent());
					String player = rec.getChildNodes().item(1).getTextContent();
					String date = rec.getChildNodes().item(2).getTextContent();
					
					list.add(new Record(score, player, date));
				}
				i++;
			}
		} catch (Exception e) { 
			fillTable();
			saveFile();
		}
	}
	
	public void fillTable() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		this.listEasy = new ArrayList<Record>();
		this.listMedium = new ArrayList<Record>();
		this.listHard = new ArrayList<Record>();
		
		for (int i = 0; i < 10; i++) {
			listEasy.add(new Record(9999, "?", dateFormat.format(new Date())));
			listMedium.add(new Record(9999, "?", dateFormat.format(new Date())));
			listHard.add(new Record(9999, "?", dateFormat.format(new Date())));
		}
	}
	
	private class RecordComparator implements Comparator<Record> {
		@Override
		public int compare(Record r1, Record r2) {
			return r1.score - r2.score;
		}
	}
}
