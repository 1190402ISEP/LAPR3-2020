#ifndef ASM_H
typedef struct{
      int battery;
      int batteryCapacity;
      int voltage;
      int timeLeft;
}Battery;

int calculate_time_to_fully_charge(int battery, int batteryCapacity, int voltage);
int calculateResto(int battery, int batteryCapacity, int voltage);
#endif
