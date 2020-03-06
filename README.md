# Data-Structures-assignment-2

####################################  IMPORTANT NOTES    #####################################
>>>>>>>>Actual program may has less or more than what I wrtote here
>>>>>>>>Variables' names may be a little bit different
>>>>>>>>No gurantte for no gramma mistakes||typos
>>>>>>>>File paths depend on your own situations
##############################################################################################

files in csv format as  pos, track name, artist, stream#, url (assummed no comma in track names)

Mian fuction : - it can extract tracknames in a csv file and create a queue holding all tracks'names by using a double ended linked list;
               - it can read in multiple csv files as long as the files addresses are sotred in allsonglistfiels.txt;
               - it ptints track names in ascending orders;
               - it has a stack class to track playlist history(but I "played" songs one by one manually;
               - it can print a lists of songs you played;
               
Song class ---works like Node;
Songlist class -- a double ended linked list, with methods as below:
                                                          *insert(String):void
                                                          *insert(String[]):void
                                                          *remove():String   --first in first out
                                                          *isEmpty():boolean
                                                          *display(File):void  
                                                          *songs():ArrayList<String> ---return an arraylist made of track names in current songlist;
                                                          *sortList():void  ----in ascending
                                                          
Queue class  ---using double ended linked list to store data, with methods as below:
                                                          *readIn(File):void
                                                          *readInMulti(File[]):void  --read in data from multi-files
                                                          *addSong(String):void
                                                          *addSong(String[]):void
                                                          *remove():String    ---first in first out
                                                          *sortQueue():void  ---in ascending order
                                                          *display(File):void
                                                          *peakfront():String
                                                          *isEmpty():boolean
                                                          *mergeQueue(Queue,Queue):Queue   ----no duplicates and in ascending orde
 PlayList class   --- a simple linked list, with methods as below:
                                                          *isEmpty():boolean
                                                          *add(String):void
                                                          *delete():String       ---last in first out
                                                          *latest():String      ---to show you the newest song
                                                          *display():void       ----print playlist history(not included the ones deleted)
                                                          
 SongHIstoryList class  ---stack, using singly linked list to store data, with methods as below:
                                                          *push():void   ---add songs
                                                          *pop():String  ----delete the latest song
                                                          *isEmpty():boolean
                                                          *displayPlaylist():void
                                                          
                                                          
                                                          
                                                          
                                                          
                                                          
                                                          
                                                          
                                                          
                                                          
                                                          
