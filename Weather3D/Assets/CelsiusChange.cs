using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class CelsiusChange : MonoBehaviour {

    public string city;
    public string celsiusDay;
    public string celsiusNight;
    public GameObject background;
    private string text;
    private bool isClick;
    // Use this for initialization
    void Start () {
        isClick = false;
	}
	
	// Update is called once per frame
	void Update () {
        
        if (background.transform.position.z == 27.2f)
            text = city + "\n" + celsiusDay + " °C";
        else
            text = city + "\n" + celsiusNight + " °C";
      
        if(isClick)
        {
            GetComponent<TextMesh>().text = text;
        }

        /*if(GetComponent<FollowHand>().GetHandClosed(0))
        {
            if (!isClick)
            {
                GetComponent<TextMesh>().text = text;
                isClick = true;
            }
            else
            {
                GetComponent<TextMesh>().text = "";
                isClick = false;
            }
        }*/

    }

    void OnMouseDown()
    {
        
            if (!isClick)
            {
                GetComponent<TextMesh>().text = text;
                isClick = true;
            }
            else
            {
                GetComponent<TextMesh>().text = "";
                isClick = false;
            }
        
    }

}
