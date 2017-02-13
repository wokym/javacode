package mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBJDBC {
	  // ���ӵ� mongodb ����
	static MongoClient mongoClient = new MongoClient("172.18.130.122",10001);
	   public static void main( String args[] ){
//		   addData();
		   showData();
//		   updateData();
//		   removeData();
		  }
	   public static void addData(){
		   try{   
		         // ���ӵ����ݿ�
		         MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");  
		         System.out.println("Connect to database successfully");
		         
		         MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		         System.out.println("���� test ѡ��ɹ�");
		         //�����ĵ�  
		         /** 
		         * 1. �����ĵ� org.bson.Document ����Ϊkey-value�ĸ�ʽ 
		         * 2. �����ĵ�����List<Document> 
		         * 3. ���ĵ����ϲ������ݿ⼯���� mongoCollection.insertMany(List<Document>) ���뵥���ĵ������� mongoCollection.insertOne(Document) 
		         * */
		         Document document = new Document("title", "MongoDB").  
		         append("description", "database").  
		         append("likes", 100).  
		         append("by", "Fly");  
		         List<Document> documents = new ArrayList<Document>();  
		         documents.add(document);  
		         collection.insertMany(documents);  
		         System.out.println("�ĵ�����ɹ�");  
		      }catch(Exception e){
		         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      }
	   }
	   
	   public static void showData(){
		   try{   
		         // ���ӵ����ݿ�
		         MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");  
		         System.out.println("Connect to database successfully");
		         
		         MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		         System.out.println("���� test ѡ��ɹ�");
		         
		         //���������ĵ�  
		         /** 
		         * 1. ��ȡ������FindIterable<Document> 
		         * 2. ��ȡ�α�MongoCursor<Document> 
		         * 3. ͨ���α�������������ĵ����� 
		         * */
		         
		         FindIterable<Document> findIterable = collection.find();  
		         MongoCursor<Document> mongoCursor = findIterable.iterator();  
		         while(mongoCursor.hasNext()){  
		            System.out.println(mongoCursor.next());  
		         }  
		      
		      }catch(Exception e){
		         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      }
		   
	   }
	   public static void updateData(){
		   try{   
		         // ���ӵ����ݿ�
		         MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");  
		         System.out.println("Connect to database successfully");
		         
		         MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		         System.out.println("���� test ѡ��ɹ�");
		         
		         //�����ĵ�   ���ĵ���likes=100���ĵ��޸�Ϊlikes=200   
		         collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));  
		         //�����鿴���  
		         FindIterable<Document> findIterable = collection.find();  
		         MongoCursor<Document> mongoCursor = findIterable.iterator();  
		         while(mongoCursor.hasNext()){  
		            System.out.println(mongoCursor.next());  
		         }  
		      
		      }catch(Exception e){
		         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      }
	   }
	   public static void removeData(){
		   try{   
		         // ���ӵ����ݿ�
		         MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");  
		         System.out.println("Connect to database successfully");

		         MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		         
		         System.out.println("���� test ѡ��ɹ�");

		         //ɾ�����������ĵ�һ���ĵ�  
		         collection.deleteOne(Filters.eq("likes", 200));  
		         //ɾ�����з����������ĵ�  
		         collection.deleteMany (Filters.eq("likes", 200));  
		         //�����鿴���  
		         FindIterable<Document> findIterable = collection.find();  
		         MongoCursor<Document> mongoCursor = findIterable.iterator();  
		         while(mongoCursor.hasNext()){  
		           System.out.println(mongoCursor.next());  
		         }
		         
		      }catch(Exception e){
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     }
	   }
	   
	   private static void userNameConnection(){
		   try {  
	            //���ӵ�MongoDB���� �����Զ�����ӿ����滻��localhost��Ϊ����������IP��ַ  
	            //ServerAddress()���������ֱ�Ϊ ��������ַ �� �˿�  
	            ServerAddress serverAddress = new ServerAddress("172.18.130.122",10001);  
	            List<ServerAddress> addrs = new ArrayList<ServerAddress>();  
	            addrs.add(serverAddress);  
	              
	            //MongoCredential.createScramSha1Credential()���������ֱ�Ϊ �û��� ���ݿ����� ����  
	            MongoCredential credential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());  
	            List<MongoCredential> credentials = new ArrayList<MongoCredential>();  
	            credentials.add(credential);  
	            //ͨ��������֤��ȡMongoDB����  
	            MongoClient mongoClient = new MongoClient(addrs,credentials);  
	            
	            //���ӵ����ݿ�  
	            MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");  
	            System.out.println("Connect to database successfully");
	            
	        } catch (Exception e) {  
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());  
	        } 
	   }
}
