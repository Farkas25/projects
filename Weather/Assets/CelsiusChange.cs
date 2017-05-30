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
        if (background.GetComponent<SpriteRenderer>().sortingLayerName == "Background2")
            text = city + celsiusDay + " °C";
        else
            text = city + celsiusNight + " °C";

        if(isClick)
            GetComponent<Text>().text = text;

        if (Input.GetMouseButtonDown(0) &&
             Camera.main.ScreenToWorldPoint(Input.mousePosition).x <= transform.position.x + 2.0f &&
             Camera.main.ScreenToWorldPoint(Input.mousePosition).x >= transform.position.x - 2.0f &&
             Camera.main.ScreenToWorldPoint(Input.mousePosition).y <= transform.position.y + 2.0f &&
             Camera.main.ScreenToWorldPoint(Input.mousePosition).y >= transform.position.y - 2.0f)
        {
            if (!isClick)
            {
                GetComponent<Text>().text = text;
                isClick = true;
            }

            else
            {
                GetComponent<Text>().text = "";
                isClick = false;
            }
        }
    }
}
