import 'package:flutter/material.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({ Key? key }) : super(key: key);

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xFFF9F9F9) ,
      appBar: AppBar(
        bottom: PreferredSize(
          preferredSize: Size.fromHeight(0.5), child: Container(
            color: Colors.black38,
            height: 0.75,
          ),
        ),
        elevation: 0,
        centerTitle: true,
         backgroundColor: Color(0xFFF9F9F9),
        title: Container(
          margin: EdgeInsets.only(top: 10),
          child: SizedBox(
            height: 60,
            child: Image.network("https://logos-marcas.com/wp-content/uploads/2020/04/Instagram-Logotipo-2016-Presente.png")),
        ),
        leading: IconButton(onPressed: (){}, icon: Icon(Icons.camera_alt, color: Colors.black, size: 30,),
      ),
      actions: <Widget>[
        IconButton(onPressed: (){}, icon: Icon(Icons.tv_sharp, color: Colors.black, size: 30,)),
        IconButton(onPressed: (){}, icon: Icon(Icons.send_outlined, color: Colors.black, size: 30,))
      ],
      
    ),
    body: Column(
      children: <Widget>[
        Container()
      ],
    )
    );

    
  }

  

  

}