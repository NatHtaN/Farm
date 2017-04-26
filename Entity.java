package newIo;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
import java.util.Set;

public class Entity{
	private String name;
	private final String type;
	public String getType() {
		return type;
	}
	private String toNonNull(Object s){
		return s==null?" ":s.toString().toLowerCase();
	}
	public Entity(String name, Object... fields ){
//		if(fields.length < 2)
//			this.type = this.getClass().getSimpleName();
//		else{
//			String n = toNonNull(fields[0]);
//			String t = toNonNull(fields[1]);
//			if(n.equals("yes") && !t.contains(" "))
//				this.type = t;
//			else{
//				this.type = this.getClass().getSimpleName();
//			}
//			
//		}
		
		if(fields!=null && fields.length>=2 
				&& toNonNull(fields[0]).equals("yes") 
				&& toNonNull(fields[1]).trim().length()>0)
			this.type = fields[1].toString().toLowerCase();
		else
			this.type = this.getClass().getSimpleName();
			
		this.name = name;
		if(fields!= null){
			for (Object field : fields) {
				if(field == null)
					continue;
				String temp = field.toString().trim();
				if(temp.length()==0)
					continue;
				addMember(temp);
			}
		}
	}
	public HashMap<String, String> members = new HashMap<String, String>();
	public ArrayList<String> fields = new ArrayList<String>();
	//member has it's own type, regardless of primitive type or class type
	/*
	 * so what kind of structure could achieve the expectation:
	 * store whatever member with its value in file, when we read it
	 */
	
	//transform of general setter for members.
	public void addMember(String field){
		fields.add(field);
	}
	
	public void setValues(Object...values){
		
		if(values.length == fields.size())
		{
			int i = 0;
			for (String fields : fields) {
				set(fields, values[i++].toString());
			}
		}
	}
	
	public String set(String field, String value){
		if(!fields.contains(field)){
			return null;
		}
		String previous = members.remove(field);
		members.put(field, value);
		return previous;
	}
	public String get(String field){
		return members.get(field);
	}
	public Set<String> listMembers(){
		return members.keySet();
	}
	
	public String toString(){
		StringBuilder ent = new StringBuilder();
		
		ent.append(name + "_"+getType() +":");
		members.entrySet().forEach(s -> ent.append(s.getKey() + ":" + s.getValue() + ","));
//		List<Map.Entry<String, String>> list = new ArrayList<Entry<String, String>>();
//		
//		list.addAll(members.entrySet());
//		list.forEach(mem -> ent.append(mem.getKey() + ":" + mem.getValue()));
		return ent.substring(0, ent.length() -2) + ";";
	}
	
	public Entity clone(){
		Entity e = new Entity(name);
		//concurrent modification exception will be thrown with below codes.
		//because it's operating its own element while iterate them.
//		fields.forEach(mem -> addMember(mem));
		fields.forEach(mem -> e.addMember(mem));
		return e;
	}
	public boolean equalInType(Entity e){
		
		return this.getType().equals(e.getType());
	}
}
