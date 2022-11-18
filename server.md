@startuml
autonumber
client -> server : register
client <- server : broadcast userList
client -> server : send message
client <- server : broadcast

@enduml

@startuml
package serverDao{
	enum MsgEnum
	class ServerThread
	class TcpBroadcastImpl
	class TcpServerDao
	class User
}
MsgEnum -down-* ServerThread
User -down-* ServerThread
TcpBroadcastImpl -|> ServerThread
ServerThread -o TcpServerDao

package controler{
	interface TcpBroadcastImpl
	class TcpController
}

TcpServerDao -o TcpController 

@enduml
