import React, { Component } from 'react';
import { View, Text, StyleSheet, Image, TouchableOpacity, Dimensions } from 'react-native';
import addProductIcon from '../../../../media/appIcon/add_product.png';
import backIcon from '../../../../media/appIcon/back_right.png';
import pick from '../../../../api/imgpicker';

const { height } = Dimensions.get('window');

export default class Sell extends Component {
    constructor(props){
        super(props);
        this.state ={
          avatarSource: null,
          data: null
        }
      }

    show(){
        pick((source, data) => this.setState({avatarSource: source, data}));
    }

    render() {
        let img = this.state.avatarSource==null? null:
        <Image 
            source={this.state.avatarSource}
            style={{width:200, height: 200, margin: 20, alignSelf: 'center'}}
        />
        return (
            <View style={styles.container}>
                    <TouchableOpacity style={styles.wrapper} onPress={this.show.bind(this)}>
                        <Image source ={addProductIcon} style={styles.iconStyle}/>
                        <Text style={styles.textInput}>Post new product</Text>
                        <Image source ={backIcon} style={styles.iconStyle}/>
                    </TouchableOpacity>
                    {img}
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container:{
        backgroundColor: '#F6F6F6',
        flex: 1
    },wrapper: { 
        height: height / 12, 
        backgroundColor: '#fff', 
        padding: 12, 
        marginTop: 20,
        flexDirection: 'row', justifyContent: 'space-between'
    }, textInput: { 
        height: height / 23, 
        paddingLeft: 10,
        paddingVertical: 0 
    },
    iconStyle: { width: 25, height: 25 }
})