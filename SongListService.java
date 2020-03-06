import java.util.*;
import java.io.*;
public class SongListService {

	public static void main(String[] args)throws Exception {
		int numOfFiles=2;
		File[] filelist=new File[numOfFiles];
		File allfile=new File("allSonglistFiles.txt");
		Scanner sc= new Scanner(allfile);
		if(sc.hasNextLine()) {
			for(int i=0;i<filelist.length;i++) {
				if(!sc.hasNextLine()) {
					i=10;
				}
				String temp = sc.nextLine();
				filelist[i] = new File(temp);
			}		
		}
		sc.close();
		Queue q = new Queue();
		File infile= new File("WeeklySonglists.txt");
		File infile1 = new File("WeeklySonglist1.txt");
		File infile2 = new File("WeeklySonglist2.txt");
		ArrayList<Queue> qlist=q.readInMulti(filelist);
		for(Queue queue: qlist) {queue.display(infile);}
		q=q.mergeQueue(qlist.get(0), qlist.get(1));
		q.display(infile);
		qlist.get(0).sortQueue();
		qlist.get(0).display(infile1);
		qlist.get(1).sortQueue();
		qlist.get(1).display(infile2);
		SongHistoryList history=new SongHistoryList();
		history.push("Dance Monkey");
		history.push("Godzilla (feat. Juice WRLD)");
		history.push("Good News");
		history.pop();
		history.push("My Oh My (feat. DaBaby)");
		history.displayPlaylist();
		
	}//close main class

	//--------------------------------------------------------------------------
	//Song Class
	static class Song{
		public String songName;
		Song next;
		public Song(String str) {
			songName=str;
			next=null;
		}
	}//close Song class
	
	//----------------------------------------------------------------------------------
	//double ended linked list
	static class SongList{
		private Song first;
		private Song last;
		public SongList() {
			first=null;
			last=null;
		}// SongList Non-arg constructor
		
		public void insert(String songName) {
			Song nextSong= new Song(songName);
			if(first==null) {
				first=nextSong;
				last=nextSong;
			}
			else {
				last.next=nextSong;
				last=nextSong;
			}
		}//close insert(String)
		public void insert(String[] songlist) {
			for(String name: songlist) {
				insert(name);
			}
			}//overloaded insert(String[])
		public boolean isEmpty() {return first==null;}
		public String remove() {
			if(first==null) {
				return "The list is empty.";
			}
			else {
				String str=first.songName;
				first=first.next;
				return str;
			}
		}
		public void display(File outputfile)throws IOException {
			PrintWriter op= new PrintWriter(outputfile);
			Song curr=first;
		      if(curr==null) {
		    	  op.println("The playlist is currently empty.");
		    	  op.close();
		    	  return;
		      }
		      op.println("Current Play Listin ascending order: ");
		      while(curr!=null){
		        op.println(curr.songName);
		        curr=curr.next;
		      }
		      op.close();
		}	

		public ArrayList<String> songs() {
			if(first!=null) {
				ArrayList<String> s=new ArrayList<>(200);
				Song c=first;
				while((c.songName != null)) {
					s.add(c.songName);
					if(c.next==null) {
						break;
					}
					c=c.next;
				}
				return s;
			}
			return null;
		}
		public void sortList() {
			Song current=first;
			Song nextOne=null;
			String temp="";
	    	if(first==null) {
	    		return;
	    	}
	    	else {
	    		while(current!=null) {
	    			nextOne=current.next;
	    			while(nextOne!=null) {
	    				
	    				if((current.songName.compareToIgnoreCase(nextOne.songName))>0) {
	    					temp=current.songName;
	    					current.songName=nextOne.songName;
	    					nextOne.songName=temp;
	    				}
	    				nextOne=nextOne.next;
	    			}
	    			current=current.next;
	    			
	    		}
		}
	}//close sortList()
	}//close songList class
	
	static class Queue{
		private SongList recentSonglist;
		 public Queue() {
			recentSonglist= new SongList();
		}
		public void readIn(File fileName)throws Exception {
			Scanner scan= new Scanner(fileName,"UTF-8");
			int firstC=0,secondC=0;
			scan.nextLine();
		    scan.nextLine();  ////consumed CVS file explanation lines
			while(scan.hasNext()) {
				String line=scan.nextLine();
				firstC=line.indexOf(",");
				secondC=line.indexOf(",",firstC+1);
				String track0=line.substring(firstC+1,secondC);
				String track1=track0.replace("\"","");
				String track2 = track1.trim();
				recentSonglist.insert(track2);
			}
			scan.close();
		}//read in a file list
		
		public ArrayList<Queue> readInMulti(File[] filelist)throws Exception{
			if(filelist==null) {return null;}
			ArrayList<Queue> queueList= new ArrayList<>(filelist.length);
			for (int i=0;i<filelist.length;i++) {
				Queue nextQueue=new Queue();
				nextQueue.readIn(filelist[i]);
				queueList.add(nextQueue);
			}
			return queueList;
		}
		public void addSong(String s) {
			recentSonglist.insert(s);
		}
		public void addSong(String[] s) {
			recentSonglist.insert(s);
		}
		public String remove() {
			return recentSonglist.remove();
		}
		public void sortQueue() {
			recentSonglist.sortList();
		}
		public void display(File outputfile)throws Exception {
			recentSonglist.display(outputfile);
		}
		public String peakFront() {
			if(recentSonglist.isEmpty()) {return "The songlist is empty.";}
			return recentSonglist.first.songName;
		}
		
		public boolean isEmpty() {return recentSonglist.isEmpty();}
		
		public Queue mergeQueue(Queue q1, Queue q2) {
			boolean a=q1.isEmpty();
			boolean b=q2.isEmpty();
			if(a&&b) {return null;}
			else if(a||b) {
				if(a) {return q2;}
				return q1;
			}
			Set<String> merge = new HashSet<>(400);
			ArrayList<String> str1=q1.recentSonglist.songs();
			ArrayList<String> str2=q2.recentSonglist.songs();
			if(str1!=null &&str2!=null) {
			for (String s: str1) {merge.add(s);}
			for(String s: str2) {merge.add(s);}
			Queue newQueue=new Queue();
			for(String s: merge) {
				newQueue.addSong(s);
			}
			newQueue.sortQueue();
			return newQueue;
			}
			return null;
		}//close merge function
		
	}//close Queue class
	
	//------------------------------------------------------------------------
	//simple linked list
	static class PlayList{
		private Song top;
		public PlayList() {
			top=null;
		}
		public boolean isEmpty() {
			return top==null;
		}
		public void add(String track) {
			Song newSong=new Song(track);
			newSong.next=top;
			top=newSong;
		}
		public String delete() {
			Song tmp=top;
			top=top.next;
			return tmp.songName;
		}//close delete()
		public String latest() {
			return top.songName;
		}
		public void display()throws IOException {
			PrintWriter op= new PrintWriter("PlayListHistory.txt");
			Song curr=top;
		      if(curr==null) {
		    	  op.println("The playlist is currently empty.");
		    	  op.close();
		    	  return;
		      }
		      op.println("Current Play List From Top To bottom: ");
		      while(curr!=null){
		        op.println(curr.songName);
		        curr=curr.next;
		      }
		      op.close();
		}	
	}//close playList class
	
	//---------------------------------------------------------------------
	static class SongHistoryList{
		PlayList recentPlaylist;
		 public SongHistoryList() {
			recentPlaylist=new PlayList();
		}
		public void push(String s) {
			recentPlaylist.add(s);
		}
		public String pop() {
			return recentPlaylist.delete();
		}
		public boolean isEmpty() {
			return recentPlaylist.isEmpty();
		}
		public void displayPlaylist()throws IOException{
			recentPlaylist.display();
		}
	}//close SongHistoryList class
	

		
	
	
	

	
	
	
	
	
	
	
	
}//close SongListService class
