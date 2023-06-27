# FloatingActionButton
Floating Action Button is for Creating custom expandable floating action view in Android 

## Usage

Add the dependency to your build.gradle.
```xml

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```

```xml
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```


Usage on layout
```xml

<com.cvaghela.customfloatingview.floatingview.fabmenu.floatingactionmenulib.MenuLayout
        android:id="@+id/menuLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/transparent"
        android:backgroundTint="@color/transparent"
        app:fab_margin_buttom="20dp"
        app:fab_margin_right="20dp"
        app:show_text="false"
        app:fab_Size="56dp"
        app:popup_fab_margin="5dp"
        app:show_custom_size_popup="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

```

Set Listner in Java Code

```xml

menuLayout.setMainButtonColorAndIcon(R.color.orange, R.drawable.plus)
                .setListImageResource(R.drawable.add_log, R.drawable.c_task)
                .setListText("alarm", "face")
                .setOnItemClickListener(new MenuLayout.OnItemClickListener() {
                    @Override
                    public void onTextItemClickListener(int position, String str) {
                        Toast.makeText(MainActivity.this, "positiion" + position + ":" + str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onImageItemClickListener(int position, int resId) {
                        Toast.makeText(MainActivity.this, "positiion" + position + ":" + resId, Toast.LENGTH_SHORT).show();
                    }
                }).createMenu();

```
