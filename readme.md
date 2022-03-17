## Curved Bottom Navigation

A compose library to easily make beautiful bottom navigation with a lot of customization options.

![CurvedBottomNavigation](/previews/first.gif)
![CurvedBottomNavigation](/previews/second.gif)
![CurvedBottomNavigation](/previews/third.gif)

## Installation


Gradle:
```

```
Maven:
```

```

# Basic usage

Place the CurvedBottomNavigation in Scaffold bottomBar.
You can control curve of background and set tow type of items : LinearItemand CircularItem 
Here is simple example, how to set color, set duration of animations , ... as shown below:
frist you need make a list of items . items can be CircularItem or LinearItem .

```
val items = listOf(
    CircularItem(
        icon = ImageVector.vectorResource(id = R.drawable.ic_profile),
        text = "profile",
	...
    ),
	...
)


val items = listOf(
    LinearItem(
        icon = ImageVector.vectorResource(id = R.drawable.ic_profile_line),
        selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile),
        text = "profile",
	...
    ),
	...
)

```
then add CurvedBottomNavigation
```
Scaffold(
    bottomBar = {
        CurvedBottomNavigation(
            items = items,
            defaultItemIndex = 1,
            color = MaterialTheme.colors.primary,
            height = 100.dp,
            hills = 2,
            verticalControl = -30f,
            topPadding = 10.dp
        ) { index ->
            navController.navigate(routes[index]) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    }
) {
    BottomNavGraph(navHostController = navController)
}
```
CurvedBottomNavigation : 
| attr | Description |
| --- | --- |
| color | sets backGround color of CurvedBottomNavigation |
| hills | sets number of hills |
| height | sets height of CurvedBottomNavigation background |
| topPadding | sets top padding |
| verticalControl | controls curve (positive for convex and negative for concave) |
| space | sets the space between the text and icon |
| onItemClick | sets listener of whole bottom navigation |

Item : 
| attr | Description |
| --- | --- |
| iconColor | sets icon color |
| selectedIconColor | sets selected item color and if it is not initialized it takes iconColor |
| icon | icon will be displayed when item is not selected |
| selectedIcon | selectedIcon will be displayed when item is selected and if it is not initialized it displays icon instead|
| onItemSelected | sets listener for current item |
| iconSize | sets size of icon |
| animDurationMillis | sets duration for displaying animation |
| radius | sets radius of circular animation |
| textColor | sets color of text |
| textStyle | sets text style |
| textFontWeight | sets text font weight |
| textFontStyle | sets text font style |
| textFontFamily | textFontFamily |

if you set CircularItem:
| attr | Description |
| --- | --- |
| circlesColor | sets circles color |
| circlesMaxRadius | sets max circle radius |
| circlesNumber | sets number of circles |

if you set LinearItem:
| attr | Description |
| --- | --- |
| circularAnimColor | sets the color of circular line for animation |
| stroke  | sets the stroke of circular line |

