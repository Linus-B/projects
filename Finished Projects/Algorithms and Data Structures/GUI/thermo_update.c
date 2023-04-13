// Thermo Update.c for Problem 1 or project 2
// bende265

#include "thermo.h"
#include <stdio.h>

// Helper function for thermo_update()
int set_temp_from_ports(temp_t* temp){

	if (THERMO_SENSOR_PORT > 64000){
		return 1;
	}
	temp -> tenths_degrees = -500 + (THERMO_SENSOR_PORT + 32) / 64;
	temp -> is_fahrenheit = THERMO_STATUS_PORT & 1;
	if (THERMO_STATUS_PORT & 1) {
		(*temp).tenths_degrees = ((*temp).tenths_degrees * 9 ) / 5 + 320;
	}
	return 0;
}

// Helper function for thermo_update()
int set_display_from_temp(temp_t temp, int *display){
	char number_list[12] = {0b01111110, 0b00001100, 0b00110111, 0b00011111, 0b01001101, 
		0b01011011, 0b01111011, 0b00001110, 0b01111111, 0b01011111, 0, 1};
	if (temp.is_fahrenheit == 1){
		if (temp.tenths_degrees < -580 || temp.tenths_degrees > 1220){
			return 1;
		}
	}else if (temp.is_fahrenheit == 0){
		if (temp.tenths_degrees < -500 || temp.tenths_degrees > 500){
			return 1;
		}
	}else{
		return 1;
	}

	int output = 0;
	int i = 0;
	short tenth_degrees = temp.tenths_degrees;
	if (tenth_degrees < 0){
		tenth_degrees *= -1;
	}
	while(tenth_degrees > 0){;
		output |= number_list[tenth_degrees % 10] << (i * 7);
		tenth_degrees /= 10;
		i++;
	}
	if (i == 0){// Value of 0
		output |= number_list[0] << (i * 7);
		i++;
	}
	if (i == 1){// Single Digit Number
		output |= number_list[0] << (i * 7);
		i++;
	}

	if (temp.tenths_degrees < 0){
		output |= number_list[11] << (i * 7);
		i++;
	}
	if (temp.is_fahrenheit & 1){
		output |= 1 << 29;
	}else{
		output |= 1 << 28;
	}
	*display = output;
	return 0;

	

}

int thermo_update(){
	temp_t temperature;
	int err = 0;
	int output_display = 0;
	err = set_temp_from_ports(&temperature);
	err |= set_display_from_temp(temperature, &output_display);
	if (err){
		return 1;	
	}else{
		THERMO_DISPLAY_PORT = output_display;
	}
	return 0;
}
