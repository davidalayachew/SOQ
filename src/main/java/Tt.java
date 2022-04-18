class Tt implements Runnable{
    
   Monitor1 mon;


   Tt( Monitor1 mon){
      this.mon = mon; }

   @Override
   public void run(){
   
      try{
         if(mon.lengthOfList()>=2){
         
            HashMap<String,Subsekvens> map1 = mon.takeOutMap();
            HashMap<String,Subsekvens> map2 = mon.takeOutMap();
         
            HashMap<String,Subsekvens> newMap = new HashMap<String,Subsekvens>();
         
            for(String key : map1.keySet()){ newMap.put(key,map1.get(key));}
         
            for(String key : map2.keySet()){
            
               if(newMap.containsKey(key)){
                  newMap.get(key).setCounter(newMap.get(key).getCounter()+map2.get(key).getCounter());
               }else{
                  newMap.put(key,map2.get(key)); }}
         
            mon.putMapInList(newMap);}
      }catch (InterruptedException e){
         System.out.print("o"); } }}