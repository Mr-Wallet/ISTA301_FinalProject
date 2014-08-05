package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RCRhymeDictionary {

	private ArrayList<ArrayList<String>> rhymeList = new ArrayList<ArrayList<String>>();

	public List<String> getRandomRhymeList() {

		List<String> randomList;
		Random rnd = new Random();

		randomList = rhymeList.get(rnd.nextInt(rhymeList.size()));

		return randomList;
	}

	

	public void addWords(ArrayList<String> arrayList) {

		boolean found = false;

		if(rhymeList.isEmpty()){
			rhymeList.add(arrayList);
			return;
		}
		
		ArrayList<Integer> track = new ArrayList<Integer>();

		// This nested for loop is used to merge all words of array List
		for(int i=0 ; i < arrayList.size(); i++){ //for used to get each word of the parameter		
			
			// for which takes each group of arrayList in rhymeList
			for(int j=0; j < rhymeList.size(); j++){
				// for which takes each word of arrayList in rhymeList
				for(int k=0; k < rhymeList.get(j).size(); k++){

					if(arrayList.get(i).equals(rhymeList.get(j).get(k))){

						if(!track.contains(j))
							track.add(j);
						
						break;

					}

				}
			}

		}

		merge(track);

		// nested for loop which add the arrayList parameter if found the words
		// in the rhymeList. Otherwise, creates a new group of arrayList in the
		// rhymeList.
		for(int i=0 ; i < arrayList.size() && !found; i++){ //for used to get each word of the parameter

			// for which takes each group of arrayList in rhymeList
			for(int j=0; j < rhymeList.size(); j++){

				if(rhymeList.get(j).contains(arrayList.get(i))){
					rhymeList.get(j).addAll(arrayList);
					found = true;
					break;
				}

			}

		}

		// if used to verify if the arrayList in the parameter was already added into the 
		// rhymeList. If not, it will create a new group of rhymes.
		if (found == false)
			rhymeList.add(arrayList);

		

	}

	// Merge help method which merge the arrayLists in common in the rhymeList.  
	private void merge(ArrayList<Integer> track) {
		
		for(int i=track.size()-1; i>0; i--){

			rhymeList.get(track.get(0)).addAll(rhymeList.get(track.get(i)));
			rhymeList.remove((int) track.get(i));	
		}


	}

	public int getSize(){
		return rhymeList.size();
	}

	public int getSizeGivenRhymeList(int index){
		return rhymeList.get(index).size();
	}

	public List<String> getRhymeList(int index) {

		List<String> list;

		list = rhymeList.get(index);

		return list;
	}
	
	@Override
	public String toString(){
		
		String str = "{ ";
		for(int j=0; j < rhymeList.size(); j++){
			
			for(int k=0; k < rhymeList.get(j).size(); k++){
				if(k+1 == rhymeList.get(j).size())
					str += rhymeList.get(j).get(k);
				else	
				    str += rhymeList.get(j).get(k)+", ";
			}
			if(j+1 == rhymeList.size())
				str+=" }\n";
			else
				str+=" }\n{ ";
			
		}
		return str;
	}





}


